package com.springrest.user.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springrest.user.decorator.AuthResponse;
import com.springrest.user.decorator.Response;
import com.springrest.user.decorator.Unauthorized;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;
@Slf4j
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
    @Serial
    private static final long serialVersionUID = -7858869558953243875L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ObjectMapper mapper = new ObjectMapper();
        Unauthorized ua = new Unauthorized();

        response.setContentType("application/JSON");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        ua.setStatus(new Response(HttpStatus.UNAUTHORIZED, "Unauthorized", "401"));
        response.getWriter().write(mapper.writeValueAsString(ua));
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");

    }
}
