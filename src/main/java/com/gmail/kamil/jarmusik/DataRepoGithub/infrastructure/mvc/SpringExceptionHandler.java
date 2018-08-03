package com.gmail.kamil.jarmusik.DataRepoGithub.infrastructure.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
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

    private Environment environment;

    @Autowired
    public SpringExceptionHandler(Environment environment) {
        this.environment = environment;
    }

    @ExceptionHandler(Throwable.class)
    @ResponseBody
    Map<String,Object> handleControllerException(HttpServletRequest req, Throwable ex) {
        Map<String,Object> responseBody = new HashMap<>();
        responseBody.put("path",req.getRequestURI());
        responseBody.put("message",ex.getMessage());
        responseBody.put("exception",ex.getClass());
        stackTraceInResponseIfDebug(ex, responseBody);
        return responseBody;
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Map<String,Object> responseBody = new HashMap<>();
        responseBody.put("path", extractUri(request));
        responseBody.put("code",status.value());
        responseBody.put("message", "There is no such url defined in the API!");
        responseBody.put("headers",ex.getHeaders());
        responseBody.put("exception",ex.getClass());
        stackTraceInResponseIfDebug(ex, responseBody);
        return new ResponseEntity<Object>(responseBody,status);
    }

    private String extractUri(WebRequest request) {
        String description = request.getDescription(false);
        if(description == null)
            return "";
        String[] row = description.split("=");
        if(row == null || row.length == 0)
            return "";
        return row.length == 1 ? row[0] : row[1];
    }

    private void stackTraceInResponseIfDebug(Throwable ex, Map<String, Object> responseBody) {
        if("DEBUG".equals(environment.getProperty("logging.level.org.springframework.web")))
            responseBody.put("stack",ex);
    }

}