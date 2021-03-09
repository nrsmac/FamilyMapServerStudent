package Handlers;

import Request.EventRequest;
import Response.EventResponse;
import Services.EventService;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class EventHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (exchange.getRequestMethod().equalsIgnoreCase("get")){
            Headers reqHeaders = exchange.getRequestHeaders();
            if(reqHeaders.containsKey("Authorization")){
                InputStream reqBody = exchange.getRequestBody();
                String reqData = Codex.readString(reqBody);

                EventRequest request = null;
                if(exchange.getRequestURI().toString().length()==6){
                    request = new EventRequest(reqHeaders.get("Authorization").get(0));
                } else {
                    String eventID = exchange.getRequestURI().toString().substring(7);
                    request = new EventRequest(reqHeaders.get("Authorization").get(0), eventID);
                }

                EventService service = new EventService(request);
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK,0);

                EventResponse response = service.getResponse();
                String respData = Json.serialize(response);
                OutputStream respBody = exchange.getResponseBody();
                Codex.writeString(respData,respBody);

                exchange.getResponseBody().close();
            }
        }
    }
}
