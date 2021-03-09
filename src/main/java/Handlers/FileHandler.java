package Handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class FileHandler implements HttpHandler{

    /**
     * @param exchange an exchange containing JSON, get and response
     * @throws IOException on error
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        boolean success = false;
        //translate physical URLS into file paths

        //Get url from exchange object urlPath = httpExchange.getRequestURI()
            // set null or "/" to "/index.html"
        // Append urlPath to physical file name
        //Return 404 if not exists
        // If exists -> read to HTTP exchange OutputStream

        try {
            if(exchange.getRequestMethod().toLowerCase().equals("get")){
                String urlPath = exchange.getRequestURI().toString();
                if (urlPath.equals("/") || urlPath.equals("")){
                    urlPath = "/index.html";
                }
                String filePath = "web" + urlPath;
                File file = new File(filePath);
                File fourOFour = new File("HTML/404");
//                File fourOFive = new File("HTML/404");

                if (file.exists()){
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK,0);
                    OutputStream respBody = exchange.getResponseBody();
                    Files.copy(file.toPath(), respBody);
                    respBody.close();
                } else {
                    //404
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK,0);
                    OutputStream respBody = exchange.getResponseBody();
                    Files.copy(fourOFour.toPath(), respBody);
                    respBody.close();
                    System.out.println("404 error");
                }

            } else {
                //Send 405
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_METHOD, 0);
                System.out.println("405 error");
            }
        } catch (IOException e) {
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_SERVER_ERROR, 0);
            exchange.getResponseBody().close();
            e.printStackTrace();
        }

    }
}
