package com.at.internship.schedule.response;

import java.util.List;

import lombok.Data;

@Data
public class GenericResponse {
    private String timestamp;
    private String code;
    private String message;
    private List<String> content;
}
