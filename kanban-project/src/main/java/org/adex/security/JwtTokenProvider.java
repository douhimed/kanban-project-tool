package org.adex.security;

import io.jsonwebtoken.*;
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
        String userId = Integer.toString(user.getId());
        Date now = new Date(System.currentTimeMillis());
        Date expirationDate = new Date(now.getTime() + JWT_EXPIRATION_TIME);

        Map<String, Object> claims = new HashMap<>();
        claims.put("id", userId);
        claims.put("username", user.getUsername());
        claims.put("fullName", user.getFullName());

        return Jwts.builder()
                .setSubject(userId)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            System.err.println("Invalid JWT Signature");
        } catch (MalformedJwtException ex) {
            System.err.println("Invalid JWT Token");
        } catch (ExpiredJwtException ex) {
            System.err.println("Expired JWT Token");
        } catch (UnsupportedJwtException ex) {
            System.err.println("Unsupported JWT Token");
        } catch (IllegalArgumentException ex) {
            System.err.println("JWT Claims string is empty");
        }
        return false;
    }

    public int getUserIdFromToken(String token){
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return Integer.parseInt((String)claims.get("id"));
    }


}
