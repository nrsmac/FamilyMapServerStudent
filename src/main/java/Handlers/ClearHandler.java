package Handlers;

import DataAccess.DataAccessException;
import Request.ClearRequest;
import Response.ClearResponse;
import Services.ClearService;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class ClearHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().equalsIgnoreCase("post")){
            ClearRequest request = new ClearRequest();
            ClearService service = null;
            try {
                service = new ClearService(request);
            } catch (DataAccessException e) {
                e.printStackTrace();
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST,0);
            }

            ClearResponse response = service.getResponse();
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
