package com.blog.react_spring_blog.security.jwt;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;

import javax.naming.AuthenticationException;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint  {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException{
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }



}