package com.at.internship.schedule.exception;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.at.internship.schedule.response.ErrorResponse;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExceptionHandler {
    private static final String STR_NESTED_EXCEPTION = "nested exception is";
    
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ErrorResponse handleMessageNotReadableException(HttpMessageNotReadableException e) {
        e.printStackTrace();
        String errorMessage = e.getMessage();
        if (errorMessage != null && errorMessage.contains(STR_NESTED_EXCEPTION))
            errorMessage = errorMessage.substring(0, errorMessage.indexOf(STR_NESTED_EXCEPTION));
        ErrorResponse response = new ErrorResponse();
        response.setCode("No leible");
        response.setMessage("Bad Requests");
        response.setErrorMessage(Collections.singletonList(errorMessage));
        return response;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        e.printStackTrace();
        List<String> errorMessages = new ArrayList<>();
        e.getBindingResult().getAllErrors().forEach(err -> {
            if (!(err instanceof FieldError)) return;
            FieldError fieldError = (FieldError) err;
            errorMessages.add(fieldError.getDefaultMessage()); // + fieldError.getField() + " " + fieldError.getValue()
        });
        ErrorResponse response = new ErrorResponse();
        response.setCode("Argumento Invalido");
        response.setMessage("Bad Requests");
        response.setErrorMessage(errorMessages);
        return response;
    }
}
