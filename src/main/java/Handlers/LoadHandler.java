package Handlers;

import DataAccess.DataAccessException;
import Request.LoadRequest;
import Response.LoadResponse;
import Services.LoadService;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.nio.file.Files;
import java.nio.file.Path;

public class LoadHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if(exchange.getRequestMethod().equalsIgnoreCase("post")){
            Headers reqHeaders = exchange.getRequestHeaders();
//                InputStream reqBody = exchange.getRequestBody();
            String reqData = null;
            String json = Files.readString(Path.of("passoffFiles/LoadData.json"));
                LoadRequest request = Json.deserialize(json,LoadRequest.class);
            LoadService service = null;
            try {
                service = new LoadService(request);
            } catch (DataAccessException e) {
                e.printStackTrace();
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST,0);
            }


            LoadResponse response = service.getResponse();
                if (response.isSuccess()){
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK,0);
                } else {
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST,0);
                }
                String respData = Json.serialize(response);
                OutputStream respBody = exchange.getResponseBody();
                Codex.writeString(respData, respBody);
                exchange.getResponseBody().close();
            }
    }
}
