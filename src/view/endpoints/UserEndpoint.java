package view.endpoints;

import com.google.gson.Gson;
import logic.UserController;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.FormParam;

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
    @Path("/login")
    /*public void login(
            @("email") String param1) {

        System.out.print("");
        System.out.print(param1);

        Gson gson = new Gson();

        UserController ctrl = new UserController();

        //return gson.toJson(ctrl.login(cbs_email, password));
    }*/

}
