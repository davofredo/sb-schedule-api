package com.at.internship.schedule.dto;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class ContactUpdateDto {

    @NotNull(message = "Id is required")
    @Min(value = 1, message = "Id must be greater than 0")
    private Long id;
    @NotNull(message = "First name is required")
    @Length(min = 2, max = 255, message = "First name should be at least 2 characters length and 255 maximum")
    @Pattern(regexp = "[a-zA-ZÁÉÍÑÓÚÜáéíñóúü\\s]+", message = "First name should have only letters")
    private String firstName;
    @NotNull(message = "Last name is required")
    @Length(min = 2, max = 255, message = "Last name should be at least 2 characters length and 255 maximum")
    @Pattern(regexp = "[a-zA-ZÁÉÍÑÓÚÜáéíñóúü\\s]+", message = "Last name should have only letters")
    private String lastName;
    @Email(message = "Please provide a valid email address")
    private String emailAddress;
    private String birthDay;
}
