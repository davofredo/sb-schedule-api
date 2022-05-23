package com.at.internship.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericResponseContactDetailsDto {
    private String timestamp;
    private String code;
    private String message;
    private ContactDetailsDto content;

}
