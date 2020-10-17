package com.softraysolutions.api.filters;

import com.softraysolutions.hibernate.entity.User;
import com.softraysolutions.api.util.ReusableStrings;
import com.softraysolutions.security.AuthenticatedUserDetails;
import com.softraysolutions.security.JwtHelper;
import com.softraysolutions.security.TokenBasedSecurityContext;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.io.IOException;

public class AuthenticationFilter implements ContainerRequestFilter {

    private final String OPTIONS = "OPTIONS";
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String path = requestContext.getUriInfo().getPath();
        if(path.contains(ReusableStrings.LOGIN_API_PATH)
                || path.contains("/public")
                || requestContext.getMethod().equals(OPTIONS)) {
            return;
        }


        String bearerToken = requestContext.getHeaderString(ReusableStrings.HEADER_STRING);
        if(bearerToken != null && !bearerToken.isEmpty()
                && bearerToken.startsWith(ReusableStrings.TOKEN_PREFIX)) {
            bearerToken = bearerToken.substring(7);
        }


        User user = JwtHelper.validateToken(bearerToken);
        if(user != null) {
            AuthenticatedUserDetails authenticatedUserDetails = new AuthenticatedUserDetails(user.getId(), user.getEmail());
            SecurityContext securityContext = new TokenBasedSecurityContext(authenticatedUserDetails, true);
            requestContext.setSecurityContext(securityContext);
        } else {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED)
                    .entity("Unauthorized access!!!").build());
        }
    }
}
