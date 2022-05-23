package com.at.internship.schedule.response;

import java.util.List;

public class ErrorResponse extends GenericResponse {
    List<String> errorMessage;
    List<String> content;
    public List<String> getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(List<String> errorMessage) {
        this.errorMessage = errorMessage;
    }
    public List<String> getContent() {
        return content;
    }
    public void setContent(List<String> content) {
        this.content = content;
    }
}
