package edu.hm.shareitauth.resources;

import edu.hm.shareitauth.model.Token;
import edu.hm.shareitauth.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

/**
 * REST-API for authentication service
 */
@Path("/auth")
public class AuthResource {

private static final String cookieName = "accessToken";
    @POST
    @Path("/user")
    @Produces("application/json")
    @Consumes("application/json")
    public Response getToken(User user){


        String token = user.toString();

        return Response
                .status(Response.Status.OK)
                .entity("{\"message\":\"Login successful!\"}")
                .cookie(new NewCookie(cookieName,token))
                .build();
    }

    @GET
    @Path("/valid")
    @Produces("application/json")
    @Consumes("application/json")
    public Response getJWT(Token token){
        return Response
                .status(Response.Status.OK)
                //.entity("{\"message\":\""+value+"\"}")
                //.cookie(new NewCookie(cookieName,value))
                .build();
    }
}
