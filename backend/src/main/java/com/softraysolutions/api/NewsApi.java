package com.softraysolutions.api;

import com.softraysolutions.api.models.*;
import com.softraysolutions.hibernate.services.NewsService;
import com.softraysolutions.security.AuthenticatedUserDetails;
import com.softraysolutions.api.util.ReusableStrings;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;

@Path(ReusableStrings.NEWS_API_PATH)
public class NewsApi {

    @Context
    private SecurityContext securityContext;

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNews(@PathParam("id") int id) {
        try{
            NewsReturnData returnData = new NewsService().get(id);
            return Response.ok(returnData, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            return errorType(e.getMessage());
        }
    }

    @GET
    @Path("/admin/{searchParam}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response adminNews(@PathParam("searchParam") String searchParam) {
        try {
            List<NewsReturnData> dataList = new NewsService().adminNews(getCurrentUserId(), searchParam);
            return Response.ok(dataList, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            return errorType(e.getMessage());
        }
    }

    @GET
    @Path("/public/{searchParam}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response searchNews(@PathParam("searchParam") String searchParam) {
        try {
            List<NewsReturnData> dataList = new NewsService().searchNews(searchParam);
            return Response.ok(dataList, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            return errorType(e.getMessage());
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(NewsData newsData) {
        try {
            NewsReturnData returnData = new NewsService().save(newsData, getCurrentUserId());
            return Response.ok(returnData, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            return errorType(e.getMessage());
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") int id, NewsData newsData) {
        try{
            NewsReturnData returnData = new NewsService().update(id, newsData, getCurrentUserId());
            return Response.ok(returnData, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            return errorType((e.getMessage()));
        }
    }

    private int getCurrentUserId() {
        AuthenticatedUserDetails authenticatedUserDetails = (AuthenticatedUserDetails) securityContext.getUserPrincipal();
        return authenticatedUserDetails.getId();
    }

    private Response errorType(String message) {
        return Response.serverError().entity(message).build();
    }

}
