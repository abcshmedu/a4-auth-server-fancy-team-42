package edu.hm.shareitauth.resources;

import edu.hm.shareitauth.models.Token;
import edu.hm.shareitauth.models.User;
import edu.hm.shareitauth.services.AuthServiceImpl;
import edu.hm.shareitauth.services.IAuthService;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * REST-API for authentication service.
 */
@Path("/auth")
public class AuthResource {

    private IAuthService authService;

    /**
     * Constructor, a new auth-service implementation is defined.
     */
    public AuthResource() {
        authService = new AuthServiceImpl();
    }

    /**
     * Post that contains in the body a json object with the user-data.
     * @param user User with name and password
     * @return Response If successful, token is transferred, else error message with error-code
     */
    @POST
    @Path("/user")
    @Produces("application/json")
    @Consumes("application/json")
    public Response getToken(User user) {
        String token = authService.getToken(user);
        if (token.equals(AuthServiceImpl.getFail())) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity("{\"message\":\"Login failed!\"}")
                    .build();
        }
        else {
            return Response
                    .status(Response.Status.OK)
                    .entity("{\"token\":\"" + token + "\"}")
                    .build();
        }
    }

    /**
     * Post that contains in the body a json object representing the token.
     * @param token Token contains a string that represents user-name and pw.
     * @return Response with user-data or in case of failure an error-message.
     */
    @POST
    @Path("/valid")
    @Produces("application/json")
    @Consumes("application/json")
    public Response getJWT(Token token) {
        String res = authService.validateToken(token);
        return Response
                .status(Response.Status.OK)
                .entity(res)
                .build();
    }
}
