package com.at.internship.schedule.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class ContactFiltersDto {
    private String firstName;
    private String lastName;
    private String emailAddress;
    @JsonFormat(pattern = "MM/dd/yyyy HH:mm:ss")
    private Date birthday;
    private String contactNameLike;
}
