import javax.ws.rs.*;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;

@Path("/api")
public class Run {

    @GET
    @Path("/test")
    @Produces("application/json")
    public String getClichedMessage() {
        return "Hello World!";
    }

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServerFactory.create("http://localhost:9998/");

        server.start();

        System.out.println("Server running");
        System.out.println("Visit: http://localhost:9998/api");
        System.out.println("Hit return to stop...");
        System.in.read();
        System.out.println("Stopping server");
        server.stop(0);
        System.out.println("Server stopped");

        System.out.println();
    }


}
