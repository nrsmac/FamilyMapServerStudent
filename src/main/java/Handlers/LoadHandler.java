package Handlers;

import Request.LoadRequest;
import Response.LoadResponse;
import Services.LoadService;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class LoadHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if(exchange.getRequestMethod().equalsIgnoreCase("post")){
            Headers reqHeaders = exchange.getRequestHeaders();
            if(reqHeaders.containsKey("Authorization")){
                InputStream reqBody = exchange.getRequestBody();
                String reqData = Codex.readString(reqBody);
                LoadRequest request = Json.deserialize(reqData,LoadRequest.class);
                LoadService service = new LoadService(request);
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK,0);

                LoadResponse response = service.getResponse();
                String respData = Json.serialize(response);
                OutputStream respBody = exchange.getResponseBody();
                Codex.writeString(respData, respBody);
                exchange.getResponseBody().close();
            }
        }
    }
}
