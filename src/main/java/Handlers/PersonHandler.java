package Handlers;

import Request.PersonRequest;
import Response.PersonResponse;
import Services.PersonService;
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
        if(exchange.getRequestMethod().equalsIgnoreCase("get")){
            Headers reqHeaders =exchange.getRequestHeaders();
            if(reqHeaders.containsKey("Authorization")){
                InputStream reqBody = exchange.getRequestBody();
                String reqData = Codex.readString(reqBody);

                PersonRequest request = null;
                if(exchange.getRequestURI().toString().length()==7){
                    request = new PersonRequest(reqHeaders.get("Authorization").get(0));
                } else {
                    String personID = exchange.getRequestURI().toString().substring(8);
                    request = new PersonRequest(reqHeaders.get("Authorization").get(0), personID);
                }
                PersonService service = new PersonService(request);
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK,0);

                PersonResponse response = service.getResponse();
                String respData = Json.serialize(response);
                OutputStream respBody = exchange.getResponseBody();
                Codex.writeString(respData,respBody);

                exchange.getResponseBody().close();
            }
        }
    }
}
