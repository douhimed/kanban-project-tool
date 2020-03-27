package org.adex.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.adex.web.models.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.adex.security.SecurityConstants.JWT_EXPIRATION_TIME;
import static org.adex.security.SecurityConstants.SECRET_KEY;

@Component
public class JwtTokenProvider {

    public String generateToken(Authentication authentication) {

        User user = (User) authentication.getPrincipal();
        String userid = Integer.toString(user.getId());
        Date now = new Date(System.currentTimeMillis());
        Date expirationDate = new Date(now.getTime() + JWT_EXPIRATION_TIME);

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", userid);
        claims.put("username", user.getUsername());
        claims.put("fullName", user.getFullName());

        return Jwts.builder()
                .setSubject(userid)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

}
