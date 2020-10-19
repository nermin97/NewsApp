package com.softraysolutions.api;
import com.softraysolutions.api.models.UserAuth;
import com.softraysolutions.api.models.UserCredentials;
import com.softraysolutions.hibernate.entity.User;
import com.softraysolutions.hibernate.entity.enumeration.Enumerations;
import com.softraysolutions.hibernate.services.UserService;
import com.softraysolutions.api.util.ReusableStrings;
import com.softraysolutions.security.AuthenticatedUserDetails;
import com.softraysolutions.security.JwtHelper;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path(ReusableStrings.AUTHORIZATION_API_PATH)
public class Auth {

    @Context
    private SecurityContext securityContext;

    @POST
    @Path(ReusableStrings.LOGIN_API_PATH)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(UserCredentials uc) {
        try{
            Response response = validateCredentials(uc);
            if(response != null) return response;

            User user = new UserService().get(uc);
            if(user == null) {
                return errorType("User does not exist!");
            }
            String token = JwtHelper.generateToken(user);
            UserAuth userAuth = new UserAuth(token, user.getEmail(), user.getUserType().toString());
            return Response.ok(userAuth, MediaType.APPLICATION_JSON).build();
        } catch (Exception e){
            return errorType(e.getMessage());
        }
    }

    @POST
    @Path(ReusableStrings.NEW_USER)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(UserCredentials uc){
        try{
            Response response = validateCredentials(uc);
            if(response != null) return response;
            if(new UserService().getByEmail(uc.getEmail()) != null)
                return errorType("Email is taken!");

            User user = new UserService().save(uc, Enumerations.UserType.ChildAdministrator);
            return Response.ok(user, MediaType.APPLICATION_JSON).build();
        } catch (Exception e){
            return errorType(e.getMessage());
        }
    }

    // Returns user email if user is authenticated
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserEmail() {
        try {
            AuthenticatedUserDetails authenticatedUserDetails = (AuthenticatedUserDetails) securityContext.getUserPrincipal();
            User user = new UserService().getById(authenticatedUserDetails.getId());
            return Response.ok(user.getEmail(), MediaType.APPLICATION_JSON).build();
        } catch (Exception e){
            return errorType(e.getMessage());
        }
    }

    private Response errorType(String message) {
        return Response.serverError().entity(message).build();
    }

    private Response validateCredentials(UserCredentials uc) {
        if(uc.getEmail().isEmpty())
            return errorType("Email field is empty!");
        if(uc.getPassword().isEmpty())
            return errorType("Password field is empty!");
        return null;
    }



}

