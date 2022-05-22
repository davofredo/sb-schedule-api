package com.at.internship.schedule.response;

import java.util.List;

import lombok.Data;

@Data
public class ErrorResponse extends GenericResponse {
    List<String> errorMessage;
}
