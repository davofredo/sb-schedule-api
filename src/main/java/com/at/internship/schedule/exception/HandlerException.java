package com.at.internship.schedule.exception;

import com.at.internship.schedule.constants.MessageConstants;
import com.at.internship.schedule.response.ErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.*;

@RestControllerAdvice
public class HandlerException {
    private static final String STR_NESTED_EXCEPTION = "nested exception is";
    private static final String STR_FOR_KEY_CONSTRAINT_EXCEPTION = "for key";

    @ExceptionHandler(value = { MethodArgumentNotValidException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        final List<String> errorMessages = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(err -> {
            if(!(err instanceof FieldError)) return;
            FieldError fieldError = (FieldError) err;
            errorMessages.add(fieldError.getDefaultMessage());
        });
        ErrorResponse response = new ErrorResponse();
        response.setTimestamp(LocalDateTime.now());
        response.setCode(MessageConstants.STR_CODE_VALIDATION_FAILED);
        response.setMessage(MessageConstants.STR_MESSAGE_VALIDATION_FAILED);
        response.setErrorMessages(errorMessages);
        return response;
    }

    @ExceptionHandler(value = { MethodArgumentTypeMismatchException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        String errorMessage =
                String.format("Please provide a valid %s value for param %s", ex.getParameter().getParameterType(),
                        ex.getName());
        ErrorResponse response = new ErrorResponse();
        response.setTimestamp(LocalDateTime.now());
        response.setCode(MessageConstants.STR_CODE_BAD_REQUEST);
        response.setMessage(MessageConstants.STR_MESSAGE_BAD_REQUEST);
        response.setErrorMessages(Collections.singletonList(errorMessage));
        return response;
    }

    @ExceptionHandler(value = {EntityNotFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleEntityNotFoundException(EntityNotFoundException ex) {
        String errorMessage = ex.getMessage();
        if(errorMessage != null && errorMessage.contains(STR_NESTED_EXCEPTION))
            errorMessage = errorMessage.substring(0, errorMessage.indexOf(STR_NESTED_EXCEPTION));
        ErrorResponse response = new ErrorResponse();
        response.setTimestamp(LocalDateTime.now());
        response.setCode(MessageConstants.STR_CODE_RECORD_NOT_FOUND);
        response.setMessage(MessageConstants.STR_MESSAGE_RECORD_NOT_FOUND);
        response.setErrorMessages(Collections.singletonList(errorMessage));
        return response;
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class })
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleConstraintViolationException(DataIntegrityViolationException ex) {
        String errorMessage = ex.getMostSpecificCause().getMessage();
        if(errorMessage != null && errorMessage.contains(STR_FOR_KEY_CONSTRAINT_EXCEPTION))
            errorMessage = errorMessage.substring(0, errorMessage.indexOf(STR_FOR_KEY_CONSTRAINT_EXCEPTION));
        ErrorResponse response = new ErrorResponse();
        response.setTimestamp(LocalDateTime.now());
        response.setCode(MessageConstants.STR_CODE_DB_CONSTRAINT_VIOLATION);
        response.setMessage(MessageConstants.STR_MESSAGE_DB_CONSTRAINT_VIOLATION);
        response.setErrorMessages(Collections.singletonList(errorMessage));
        return response;
    }

    @ExceptionHandler(value = { Exception.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(Exception ex) {
        String errorMessage = ex.getMessage();
        if(errorMessage != null && errorMessage.contains(STR_NESTED_EXCEPTION))
            errorMessage = errorMessage.substring(0, errorMessage.indexOf(STR_NESTED_EXCEPTION));
        ErrorResponse response = new ErrorResponse();
        response.setTimestamp(LocalDateTime.now());
        response.setCode(MessageConstants.STR_CODE_INTERNAL_SERVER_ERROR);
        response.setMessage(MessageConstants.STR_MESSAGE_INTERNAL_SERVER_ERROR);
        response.setErrorMessages(Collections.singletonList(errorMessage));
        return response;
    }

}
