package com.at.internship.schedule.dto;
import javax.validation.constraints.*;

import com.at.internship.schedule.Constants.Validation;
import lombok.Data;

@Data
public class ContactUpdateDto {
    @NotNull(message = Validation.NOT_NULL_ID_VALIDATION)
    private Integer id;

    @NotNull(message = Validation.NOT_NULL_FIRST_NAME_VALIDATION)
    @Size(min = 2, max = 50, message = Validation.LENGTH_FIRST_NAME_VALIDATION)
    private String firstName;

    @NotNull(message = Validation.NOT_NULL_LAST_NAME_VALIDATION)
    @Size(min = 2, max = 50, message = Validation.LENGTH_LAST_NAME_VALIDATION)
    private String lastName;

    @Email(message = Validation.VALID_EMAIL_VALIDATION)
    private String emailAddress;

    @NotNull(message = Validation.NOT_NULL_BIRTH_DAY_VALIDATION)
    private String birthDay;
}
