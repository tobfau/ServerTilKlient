package view.endpoints;

import com.google.gson.Gson;
import logic.UserController;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

/**
 * Created by Kasper on 19/10/2016.
 */

@Path("/api/user")
public abstract class UserEndpoint {

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

}
