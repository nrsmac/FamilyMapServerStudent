import Handlers.*;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * The main driver for the FamilyMap API and web server.
 */

public class Server {

    // The maximum number of waiting incoming connections to queue.
    // While this value is necessary, for our purposes it is unimportant.
    // Take CS 460 for a deeper understanding of what it means.
    private static final int MAX_WAITING_CONNECTIONS = 12;

    // Java provides an HttpServer class that can be used to embed
    // an HTTP server in any Java program.
    // Using the HttpServer class, you can easily make a Java
    // program that can receive incoming HTTP requests, and respond
    // with appropriate HTTP responses.
    // HttpServer is the class that actually implements the HTTP network
    // protocol (be glad you don't have to).
    // The "server" field contains the HttpServer instance for this program,
    // which is initialized in the "run" method below.
    /**
     * The HttpServer object we interact with.
     */
    private HttpServer server;

    /**
     * Runs the family map server
     * @param portNumber the port we run.
     */
    private void run(String portNumber){
        System.out.println("Initializing HTTP Server");
        try {
            server = HttpServer.create(
                    new InetSocketAddress(Integer.parseInt(portNumber)),
                    MAX_WAITING_CONNECTIONS);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        server.setExecutor(null);

        //Create contexts
        System.out.println("Creating contexts");
        server.createContext("/", new FileHandler());
        server.createContext("/user/register", new RegisterHandler());
        server.createContext("/user/login", new LoginHandler());
        server.createContext("/clear", new ClearHandler());
        server.createContext("/fill/[username]/{generations}", new FillHandler()); //username, generations
        server.createContext("/load", new LoadHandler());
        server.createContext("/person/[person_id]", new PersonHandler());//pass in person ID for one person
        server.createContext("/person", new PersonHandler()); // all persons of current user, determined by auth token
        server.createContext("/event/[event_id]", new EventHandler()); //returns specific event
        server.createContext("/event", new EventHandler()); // all events of all family members of current user

        System.out.println("Starting server");
        server.start();
        System.out.println("Server started");
    }

    /**
     * calls Server.run and runs the family map server
     * @param args command-line arguments
     * accepts a port number between 1-65535 as an argument.
     * The default port is 8080.
     */
    public static void main(String[] args) {
        //TODO Test for default port number
        String portNumber = args[0];
        new Server().run(portNumber);
    }
}
