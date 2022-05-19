package com.at.internship.schedule.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
public class ContactUpdateDto {

    @NotNull(message = "Id is required")
    @Min(value = 1, message = "Id must be greater than 0")
    private Long id;
    @NotNull(message = "First name is required")
    @Pattern(regexp = "[a-zA-ZÁÉÍÑÓÚÜáéíñóúü]+")
    private String firstName;
    @NotNull(message = "Last name is required")
    @Pattern(regexp = "[a-zA-ZÁÉÍÑÓÚÜáéíñóúü]+")
    private String lastName;
    @Email(message = "Invalid email address")
    private String emailAddress;
    private LocalDate birthDay;
}
