import javax.ws.rs.*;

import com.google.gson.Gson;
import com.sun.deploy.config.Config;
import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import logic.CBSParser;
import logic.ConfigLoader;
import logic.UserController;
import service.Service;
//TODO: Missing documentation and use of config variables.

@Path("/api")
public class Run {

    @GET
    @Path("/getLectures/{courseId}")
    public String getLectures(@PathParam("courseId") int courseId) {
        Gson gson = new Gson();

        UserController ctrl = new UserController();

        return gson.toJson(ctrl.getLectures(courseId));
    }

    @GET
    @Path("/getCourses/{userId}")
    public String getCourses(@PathParam("userId") int userId) {
        Gson gson = new Gson();

        UserController ctrl = new UserController();

        return gson.toJson(ctrl.getCourses(userId));
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
