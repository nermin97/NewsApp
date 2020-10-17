package com.softraysolutions.security;

import javax.ws.rs.core.SecurityContext;
import java.security.Principal;

public class TokenBasedSecurityContext implements SecurityContext {

    private AuthenticatedUserDetails authenticatedUserDetails;
    private final boolean secure;

    public TokenBasedSecurityContext(AuthenticatedUserDetails authenticatedUserDetails, boolean secure) {
        this.authenticatedUserDetails = authenticatedUserDetails;
        this.secure = secure;
    }

    @Override
    public Principal getUserPrincipal() {
        return authenticatedUserDetails;
    }

    @Override
    public boolean isUserInRole(String s) {
        return false;
    }

    @Override
    public boolean isSecure() {
        return false;
    }

    @Override
    public String getAuthenticationScheme() {
        return null;
    }
}
