package com.vmware.bookmanagement.advice;

import com.vmware.bookmanagement.exception.BookNotFoundException;
import com.vmware.bookmanagement.dto.ApiResponse;
import com.vmware.bookmanagement.util.DateTimeCalculator;
import com.vmware.bookmanagement.util.ResponseCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationErrorHandler {

    @Autowired
    ResponseCreator responseCreator;
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> handleInvalidFields(MethodArgumentNotValidException ex)
    {
        Map<String, String> map = new HashMap<>();
        map.put("status",String.valueOf(HttpStatus.BAD_REQUEST.value()));
        map.put("timeStamp", DateTimeCalculator.getCurrentTime());
        ex.getBindingResult().getFieldErrors().forEach(error->{
            map.put(error.getField(), error.getDefaultMessage());
        });

        return map;
    }

    @ExceptionHandler
    public ApiResponse handleException(BookNotFoundException ex) {
        return responseCreator.createResponse(HttpStatus.NOT_FOUND, "Bad Request");
    }

    @ExceptionHandler
    public ApiResponse handleException(Exception ex) {
        return responseCreator.createResponse(HttpStatus.BAD_REQUEST, "Bad Request");

    }

}
