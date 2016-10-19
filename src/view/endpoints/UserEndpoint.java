package view.endpoints;

import com.google.gson.Gson;
import logic.UserController;

import javax.ws.rs.*;

/**
 * Created by Kasper on 19/10/2016.
 */

@Path("/api/user")
public class UserEndpoint {

    /**
     * En metode til at hente lektioner for et enkelt kursus i form af en JSON String.
     * @param courseId Id'et på det kursus man ønsker at hente.
     * @return En JSON String
     */
    @GET
    @Path("/getLectures/{courseId}")
    public String getLectures(@PathParam("courseId") int courseId) {
        Gson gson = new Gson();

        UserController ctrl = new UserController();

        return gson.toJson(ctrl.getLectures(courseId));
    }

    /**
     * En metode til at hente de kurser en bruger er tilmeldt.
     * @param userId Id'et på den bruger man ønsker at hente kurser for.
     * @return De givne kurser i form af en JSON String.
     */
    @GET
    @Path("/getCourses/{userId}")
    public String getCourses(@PathParam("userId") int userId) {

        Gson gson = new Gson();

        UserController ctrl = new UserController();

        return gson.toJson(ctrl.getCourses(userId));
    }

    @POST
    @Consumes("application/json")
    @Path("/login")
    public String login(String s) {

        System.out.println(s);

        String email = "123@123.dk";
        String password = "123";

        Gson gson = new Gson();

        UserController ctrl = new UserController();

        return gson.toJson(ctrl.login(email, password));
    }

}
