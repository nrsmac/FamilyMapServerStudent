package Handlers;

import DataAccess.DataAccessException;
import Request.RegisterRequest;
import Response.RegisterResponse;
import Services.RegisterService;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class RegisterHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().equalsIgnoreCase("post")) {
            Headers reqHeaders = exchange.getRequestHeaders();
                InputStream reqBody = exchange.getRequestBody();
                String reqData = Codex.readString(reqBody);
                //Create request object and pass to service
                RegisterRequest request = Json.deserialize(reqData,RegisterRequest.class);
            RegisterService service = null;
            try {
                service = new RegisterService(request);
            } catch (DataAccessException e) {
                e.printStackTrace();
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST,0);
            }

            //The response!
                RegisterResponse response = service.getResponse();
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
