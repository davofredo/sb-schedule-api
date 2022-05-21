package com.at.internship.schedule.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class ContactDto{
    @Max(value = 11, message = "Id should have 11 digits or less")
    private Integer id;
    @NotNull(message = "First name is required")
    @Min(value = 2, message = "First name should be at least two characters length")
    @Max(value = 50, message = "First name should have 50 as a maximum")
    private String firstName;
    @NotNull(message = "First name is required")
    @Min(value = 2, message = "Last name should be at least two characters length")
    @Max(value = 50, message = "Last name should have 50 as a maximum")
    private String lastName;
    @Email(message = "Please provide a valid email address")
    private String emailAddress;
    private String birthDay;
}