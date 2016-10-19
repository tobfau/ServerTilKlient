import javax.ws.rs.*;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.io.PrintStream;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import service.Service;
//TODO: Missing documentation and use of config variables.

@Path("/api")
public class Run {

    @GET
    @Path("/getLectures/{userId}")
    public int getLectures(@PathParam("userId") int userId) {
        //System.out.println(getLectures(3));
        return userId;
    }

    public static void main(String[] args) throws IOException {
        HttpServer server = null;

        try {
            PrintStream stdout = System.out;
            System.setOut(null);
            server = HttpServerFactory.create("http://localhost:9998/");
            System.setOut(stdout);
        }catch(ArrayIndexOutOfBoundsException a){
            System.out.println(a.getMessage());
            System.exit(20);
        }

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
