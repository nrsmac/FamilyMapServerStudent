package Handlers;

import Request.PersonsRequest;
import Response.PersonsResponse;
import Services.PersonsService;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class PersonHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            if (exchange.getRequestMethod().equalsIgnoreCase("get")) {
                Headers reqHeaders = exchange.getRequestHeaders();
                if (reqHeaders.containsKey("Authorization")) {
                    InputStream reqBody = exchange.getRequestBody();
                    String reqData = Codex.readString(reqBody);

                    PersonsRequest request = null;
                    if (exchange.getRequestURI().toString().length() == 7) {
                        request = new PersonsRequest(reqHeaders.get("Authorization").get(0));
                    } else {
                        String personID = exchange.getRequestURI().toString().substring(8);
                        request = new PersonsRequest(reqHeaders.get("Authorization").get(0), personID);
                    }
                    PersonsService service = null;
                    try {
                        service = new PersonsService(request);
                    } catch (Exception e) {
                        e.printStackTrace();
                        exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                    }
                    PersonsResponse response = service.getResponse();
                    if (response.isSuccess()) {
                        exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
                    } else {
                        exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);
                    }
                    String respData = Json.serialize(response);
                    OutputStream respBody = exchange.getResponseBody();
                    Codex.writeString(respData, respBody);

                    exchange.getResponseBody().close();
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
