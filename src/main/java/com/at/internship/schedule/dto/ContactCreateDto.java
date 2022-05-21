package com.at.internship.schedule.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
public class ContactCreateDto {

    @NotNull(message = "First name is required")
    @Pattern(regexp = "[a-zA-ZÁÉÍÑÓÚÜáéíñóúü]+")
    private String firstName;
    @NotNull(message = "Last name is required")
    @Pattern(regexp = "[a-zA-ZÁÉÍÑÓÚÜáéíñóúü]+")
    private String lastName;
    @Email(message = "Invalid email address")
    private String emailAddress;
    private String birthDay;
}
