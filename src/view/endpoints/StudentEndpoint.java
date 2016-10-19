package view.endpoints;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * Created by Kasper on 19/10/2016.
 */
public class StudentEndpoint extends UserEndpoint {

    @POST
    @Path("/getLectures/{courseId}")
    public static void createReview(String json){

    }


}
