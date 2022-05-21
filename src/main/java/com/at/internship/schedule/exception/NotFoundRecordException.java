package com.at.internship.schedule.exception;

public class NotFoundRecordException extends RuntimeException{

    public NotFoundRecordException(String message){
        super(message);
    }
}
