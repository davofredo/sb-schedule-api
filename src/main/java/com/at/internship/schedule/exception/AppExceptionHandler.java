package com.at.internship.schedule.exception;

import java.util.Collections;

import com.at.internship.schedule.response.ErrorResponse;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

@ControllerAdvice
public class AppExceptionHandler {
    private static final String STR_NESTED_EXCEPTION = "nested exception is";
    
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ErrorResponse handleDefaultHandlerExceptionResponse(HttpMessageNotReadableException e) {
        e.printStackTrace();
        String errorMessage = e.getMessage();
        if (errorMessage != null && errorMessage.contains(STR_NESTED_EXCEPTION))
            errorMessage = errorMessage.substring(0, errorMessage.indexOf(STR_NESTED_EXCEPTION));
        ErrorResponse response = new ErrorResponse();
        response.setCode("Invalid_name");
        response.setMessage("Bad Requests");
        response.setErrorMessage(Collections.singletonList(errorMessage));
        return response;
    }
}
