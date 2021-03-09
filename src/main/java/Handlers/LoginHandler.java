package Handlers;

import Request.LoginRequest;
import Request.RegisterRequest;
import Response.LoginResponse;
import Response.RegisterResponse;
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
            if(reqHeaders.containsKey("Authorization")) {
                //String authToken = reqHeaders.getFirst("Authorization");
                InputStream reqBody = exchange.getRequestBody();
                String reqData = Codex.readString(reqBody);
                LoginRequest request = Json.deserialize(reqData,LoginRequest.class);
                LoginService service = new LoginService(request);
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK,0);

                LoginResponse response = service.getResponse();
                String respData = Json.serialize(response);
                OutputStream respBody = exchange.getResponseBody();
                Codex.writeString(respData, respBody);
                exchange.getResponseBody().close();
            }
        }
    }
}
