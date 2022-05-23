package com.at.internship.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ErrorResponse extends RuntimeException{
    private String timestamp;
    private String code;
    private String message;
    private ContactDetailsDto content = null;
    private List<String> errorMessages;
}
