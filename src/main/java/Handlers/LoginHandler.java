package Handlers;

import DataAccess.DataAccessException;
import Request.LoginRequest;
import Response.LoginResponse;
import Services.LoginService;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class LoginHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().equalsIgnoreCase("post")){
            Headers reqHeaders = exchange.getRequestHeaders();
                //String authToken = reqHeaders.getFirst("Authorization");
                InputStream reqBody = exchange.getRequestBody();
                String reqData = Codex.readString(reqBody);
                LoginRequest request = Json.deserialize(reqData,LoginRequest.class);
            LoginService service = null;
            try {
                service = new LoginService(request);
            } catch (DataAccessException e) {
                e.printStackTrace();
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST,0);
            }

            LoginResponse response = service.getResponse();
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
