package edu.hm.shareitauth.resources;

import edu.hm.shareitauth.model.Token;
import edu.hm.shareitauth.model.User;
import edu.hm.shareitauth.services.AuthServiceImpl;
import edu.hm.shareitauth.services.IAuthService;

import javax.ws.rs.*;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

/**
 * REST-API for authentication service
 */
@Path("/auth")
public class AuthResource {

    private IAuthService authService;

    public AuthResource(){
        authService = new AuthServiceImpl();
    }

private static final String cookieName = "accessToken";
    @POST
    @Path("/user")
    @Produces("application/json")
    @Consumes("application/json")
    public Response getToken(User user){
        String token = authService.getToken(user);
        if(token.equals(AuthServiceImpl.fail)){
            return Response
                    .status(Response.Status.OK)
                    .entity("{\"message\":\"Login failed!\"}")
                    .build();
        }
        else {
            return Response
                    .status(Response.Status.OK)
                    .entity("{\"message\":\"Login successful!\"}")
                    .cookie(new NewCookie(cookieName,token))
                    .build();
        }
    }

    @GET
    @Path("/valid")
    @Produces("application/json")
    @Consumes("application/json")
    public Response getJWT(Token token){
        String res = authService.validateToken(token);
        return Response
                .status(Response.Status.OK)
                .entity(res)
                .build();
    }
}
