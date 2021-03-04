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
        System.out.println("Handler run!!!");
        if (exchange.getRequestMethod().toLowerCase().equals("post")) {
            Headers reqHeaders = exchange.getRequestHeaders();
            if (reqHeaders.containsKey("Authorization")) {
                String authToken = reqHeaders.getFirst("Authorization");
                //TODO Validate auth token
                //Random UUID -> java base class
                InputStream reqBody = exchange.getRequestBody();
                String reqData = readString(reqBody);
                //Create request object and pass to service
                RegisterRequest request = Json.deserialize(reqData,RegisterRequest.class);
                RegisterService service = new RegisterService(request);
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

                //The response!
                RegisterResponse response = service.getResponse();
                String respData = Json.serialize(response);
                OutputStream respBody = exchange.getResponseBody();
                writeString(respData, respBody);
                exchange.getResponseBody().close();
            }
        }
    }

    /*
		The readString method shows how to read a String from an InputStream.
	*/
    private String readString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0) {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }

    /*
		The writeString method shows how to write a String to an OutputStream.
	*/
    private void writeString(String str, OutputStream os) throws IOException {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }
}
