package org.adex.security;

import com.google.gson.Gson;
import org.adex.utilities.exceptions.InvalidLoginResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        InvalidLoginResponse invalidLoginResponse = new InvalidLoginResponse();
        String json_invalidLoginResponse = new Gson().toJson(invalidLoginResponse);

        response.setContentType("application/json");
        response.setStatus(401);
        response.getWriter().println(json_invalidLoginResponse);
    }
}
