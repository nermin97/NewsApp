package com.softraysolutions.security;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.softraysolutions.hibernate.entity.User;
import com.softraysolutions.hibernate.services.UserService;
import com.softraysolutions.api.util.ReusableStrings;

import java.io.UnsupportedEncodingException;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class JwtHelper {

    private static JwtHelper jwtHelper = null;
    private static long expirationDate = 50;

    public static String generateToken(User u) {
        try {
            return JWT.create()
                    .withIssuedAt(Date.from(ZonedDateTime.now().toInstant()))
                    .withExpiresAt(getExpirationDate())
                    .withClaim("email", u.getEmail())
                    .withIssuer(ReusableStrings.ISSUER)
                    .sign(Algorithm.HMAC256(ReusableStrings.SECRET_KEY));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static User validateToken(String token){
        try{
            if(token != null) {
                JWTVerifier verifier =
                        JWT.require(Algorithm.HMAC256(ReusableStrings.SECRET_KEY))
                        .withIssuer(ReusableStrings.ISSUER)
                        .build();
                DecodedJWT jwt = verifier.verify(token);
                Claim email = jwt.getClaim("email");
                // geting the user
                return new UserService().getByEmail(email.asString());
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Date getExpirationDate() {
        return new Date(System.currentTimeMillis()
                + TimeUnit.MINUTES.toMillis(expirationDate));
    }
}
