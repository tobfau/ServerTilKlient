import javax.ws.rs.*;

import com.sun.deploy.config.Config;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import logic.CBSParser;
import logic.ConfigLoader;
import service.Service;
//TODO: Missing documentation and use of config variables.

@Path("/api")
public class Run {

    @GET
    @Path("/getLectures/{userId}")
    public int getLectures(@PathParam("userId") int userId) {

        return 34243242;
    }

    public static void main(String[] args) throws IOException {

        //Loader configfilen
        ConfigLoader.parseConfig();

        //Loader courses og lectures ind til databasen
        System.out.println("Server henter fag og lektioner.");
        System.out.println("Det kan tage op til 40 sekunder...");
       // CBSParser.parseCoursesToArray();
        //CBSParser.parseLectures();

        //Starter server
        HttpServer server = HttpServerFactory.create("http://"+ConfigLoader.SERVER_ADDRESS+":"+ConfigLoader.SERVER_PORT+"/");
        server.start();

        //Starter MainController
        //Service service = new ServiceImpl();
        //new = MainController(service);





        System.out.println("Server running");
        System.out.println("Visit: http://localhost:9998/api");
        System.out.println("Hit return to stop...");
        System.in.read();
        System.out.println("Stopping server");
        System.out.println("Server stopped");

        System.out.println();

    }


}
