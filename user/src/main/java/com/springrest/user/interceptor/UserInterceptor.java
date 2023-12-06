package com.springrest.user.interceptor;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.springrest.user.decorator.AuthResponse;
import com.springrest.user.decorator.Response;
import com.springrest.user.decorator.Unauthorized;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;


@Slf4j
@Component
public class UserInterceptor implements HandlerInterceptor {



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object Handler) throws IOException {
//        log.info("{}",request.getHeader("Authorization"));
//        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
//            return true;
//        }else {
//            ObjectMapper mapper = new ObjectMapper();
//            Unauthorized ua = new Unauthorized();
//            response.setContentType("application/JSON");
//            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            ua.setStatus(new Response(HttpStatus.UNAUTHORIZED, "Unauthorized", "401"));
//            response.getWriter().write(mapper.writeValueAsString(ua));
//            return false;
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

//        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
//            log.info("Authenticated");
//        }else{
//            log.info("Unauthenticated");
//            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//        }

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("Completed");
    }
}















