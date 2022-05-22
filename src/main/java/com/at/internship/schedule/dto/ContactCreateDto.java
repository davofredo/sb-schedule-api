package com.at.internship.schedule.dto;

import com.at.internship.schedule.Constants.Validations;
import lombok.Data;

import javax.validation.constraints.*;

@Data
public class ContactCreateDto {

    @NotNull(message = Validations.NOT_NULL_FIRST_NAME_VALIDATION)
    @Size(min = 2, max = 50, message = Validations.LENGTH_FIRST_NAME_VALIDATION)
    private String firstName;

    @NotNull(message = Validations.NOT_NULL_LAST_NAME_VALIDATION)
    @Size(min = 2, max = 50, message = Validations.LENGTH_LAST_NAME_VALIDATION)
    private String lastName;

    @Email(message = Validations.VALID_EMAIL_VALIDATION)
    private String emailAddress;

    @NotNull(message = Validations.NOT_NULL_BIRTH_DAY_VALIDATION)
    private String birthDay;
}