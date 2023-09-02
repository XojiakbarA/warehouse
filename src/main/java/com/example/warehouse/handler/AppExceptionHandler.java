package com.example.warehouse.handler;

import com.example.warehouse.dto.Response;
import com.example.warehouse.exception.AmountExceedsException;
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
        response.setMessage(e.getMessage());
        return response;
    }

    @ExceptionHandler(AmountExceedsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Response handleAmountExceeds(AmountExceedsException e) {
        Response response = new Response();
        response.setMessage(e.getMessage());
        return response;
    }

//    @ExceptionHandler(RuntimeException.class)
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    @ResponseBody
//    public Response handle(RuntimeException e) {
//        Response response = new Response();
//        response.setMessage(HttpStatus.INTERNAL_SERVER_ERROR.name());
//        return response;
//    }
}
