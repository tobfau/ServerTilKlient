package view.endpoints;

import com.google.gson.Gson;
import logic.UserController;
import shared.UserDTO;

import javax.ws.rs.*;


@Path("/api/user")
public class UserEndpoint {

    /**
     * En metode til at hente lektioner for et enkelt kursus i form af en JSON String.
     * @param courseId Id'et på det kursus man ønsker at hente.
     * @return En JSON String
     */
    @GET
    @Path("/getLectures/{course}")
    public String getLectures(@PathParam("course") String course) {
        Gson gson = new Gson();

        UserController ctrl = new UserController();

        return gson.toJson(ctrl.getLectures(course));
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
    public String login(String data) {

        Gson gson = new Gson();
        UserDTO user = new Gson().fromJson(data, UserDTO.class);

        UserController ctrl = new UserController();

        return gson.toJson(ctrl.login(user.getCbsMail(), user.getPassword()));
    }


}
