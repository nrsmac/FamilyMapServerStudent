package Handlers;

import DataAccess.DataAccessException;
import Request.FillRequest;
import Response.FillResponse;
import Services.FillService;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class FillHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if(exchange.getRequestMethod().equalsIgnoreCase("post")){
            Headers reqHeaders = exchange.getRequestHeaders();
            InputStream reqBody = exchange.getRequestBody();
            String reqData = Codex.readString(reqBody);

            FillRequest request = null;
            String requestString = exchange.getRequestURI().toString().substring(6);
            String username = "";
            if(requestString.contains("/")) {
                int slashIndex = requestString.indexOf("/");
                username = requestString.substring(0,slashIndex);
                int generations = Integer.parseInt(requestString.substring(slashIndex+1));
                request = new FillRequest(username,generations);
            } else {
                username = requestString;
                request = new FillRequest(username);
            }

            FillService service = null;

                try {
                    service = new FillService(request);
                } catch (DataAccessException e) {
                    e.printStackTrace();
//                    System.out.println(e.getMessage());
                }


            FillResponse response = service.getResponse();

            if (response.isSuccess()){
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK,0);
            } else {
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST,0);
            }

            String respData = Json.serialize(response);
            OutputStream respBody = exchange.getResponseBody();
            Codex.writeString(respData,respBody);
            exchange.getResponseBody().close();
        }
    }
}
