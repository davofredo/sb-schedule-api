package com.at.internship.schedule.exception.handler;

import com.at.internship.schedule.exception.EmailTakenException;
import com.at.internship.schedule.exception.custom.CustomResponseEntity;
import com.at.internship.schedule.exception.NotFoundRecordException;
import com.at.internship.schedule.exception.NotNullIdException;
import com.at.internship.schedule.exception.NotValidContactException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ContactRequestHandler extends ResponseEntityExceptionHandler {

    // Not valid arguments
    @ExceptionHandler(value = {NotValidContactException.class})
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex,
        HttpHeaders headers,
        HttpStatus status,
        WebRequest request
    ) {

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        CustomResponseEntity cre = new CustomResponseEntity(
          LocalDateTime.now(),
            badRequest,
            "There were validations errors",
            errors
        );

        return new ResponseEntity<>(cre, badRequest);
    }

    @ExceptionHandler(NotFoundRecordException.class)
    public ResponseEntity<CustomResponseEntity> handleRecordNotFound(NotFoundRecordException ex) {
        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());
        CustomResponseEntity cre = new CustomResponseEntity(
            LocalDateTime.now(),
            HttpStatus.NOT_FOUND,
            "Record Not Found",
            errors
        );
    return new ResponseEntity<>(cre, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotNullIdException.class)
    public ResponseEntity<CustomResponseEntity> handleNotNullId() {
        CustomResponseEntity cre = new CustomResponseEntity(
            LocalDateTime.now(),
            HttpStatus.BAD_REQUEST,
            "ID Cannot be null"
        );
        return new ResponseEntity<>(cre, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailTakenException.class)
    public ResponseEntity<CustomResponseEntity> handleEmailTaken(EmailTakenException e) {
        CustomResponseEntity cre = new CustomResponseEntity(
            LocalDateTime.now(),
            HttpStatus.CONFLICT,
            e.getMessage()
        );
        return new ResponseEntity<>(cre, HttpStatus.NOT_FOUND);
    }
}
