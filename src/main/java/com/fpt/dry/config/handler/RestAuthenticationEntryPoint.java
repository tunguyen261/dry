package com.fpt.dry.config.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fpt.dry.object.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
@RequiredArgsConstructor
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private  final ObjectMapper objectMapper;
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e)
            throws IOException, ServletException {
        ErrorResponse errorResponse = ErrorResponse.creator()
                .errorCode(HttpStatus.UNAUTHORIZED.value())
                .message("Unauthorized").create();
                String json = objectMapper.writeValueAsString(errorResponse);
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        httpServletResponse.setCharacterEncoding("UTF-8");

        PrintWriter out = httpServletResponse.getWriter();
        out.print(json);
        out.flush();
    }
}
