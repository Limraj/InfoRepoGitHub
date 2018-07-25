package com.gmail.kamil.jarmusik.DataRepoGithub.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class SpringExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    Map<String,Object> handleControllerException(HttpServletRequest req, Throwable ex) {
        Map<String,Object> responseBody = new HashMap<>();
        responseBody.put("path",req.getRequestURI());
        responseBody.put("message",ex.getMessage());
        responseBody.put("exception",ex.getClass());
        return responseBody;
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String,Object> responseBody = new HashMap<>();
        responseBody.put("path", getUri(request));
        responseBody.put("code",status.value());
        responseBody.put("message", "There is no such url defined in the API!");
        responseBody.put("headers",ex.getHeaders());
        responseBody.put("exception",ex.getClass());
        return new ResponseEntity<Object>(responseBody,status);
    }

    private String getUri(WebRequest request) {
        String description = request.getDescription(false);
        if(description == null)
            return "";
        String[] row = description.split("=");
        if(row == null || row.length == 0)
            return "";
        return row.length == 1 ? row[0] : row[1];
    }

}