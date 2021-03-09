package Handlers;

import Model.Model;
import Request.RegisterRequest;
import Response.RegisterResponse;
import Services.RegisterService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.net.HttpURLConnection;

public class RegisterHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().toLowerCase().equals("post")) {
            Headers reqHeaders = exchange.getRequestHeaders();
                InputStream reqBody = exchange.getRequestBody();
                String reqData = Codex.readString(reqBody);
                //Create request object and pass to service
                RegisterRequest request = Json.deserialize(reqData,RegisterRequest.class);
                RegisterService service = new RegisterService(request);
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

                //The response!
                RegisterResponse response = service.getResponse();
                String respData = Json.serialize(response);
                OutputStream respBody = exchange.getResponseBody();
                Codex.writeString(respData, respBody);
                exchange.getResponseBody().close();

        }
    }


}
