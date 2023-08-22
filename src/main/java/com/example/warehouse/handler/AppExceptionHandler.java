package com.example.warehouse.handler;

import com.example.warehouse.dto.Response;
import com.example.warehouse.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public Response handleResourceNotFound(ResourceNotFoundException e) {
        Response response = new Response();
        response.setMessage(HttpStatus.NOT_FOUND.name());
        return response;
    }
}
