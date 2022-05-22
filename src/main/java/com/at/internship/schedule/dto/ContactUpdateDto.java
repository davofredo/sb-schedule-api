package com.at.internship.schedule.dto;
import javax.validation.constraints.*;

import com.at.internship.schedule.Constants.Validations;
import lombok.Data;

@Data
public class ContactUpdateDto {
    @Size(max = 11, message = Validations.ID_VALIDATION)
    @NotNull(message = Validations.NOT_NULL_ID_VALIDATION)
    private Integer id;

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
