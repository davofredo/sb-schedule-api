package com.at.internship.schedule.dto;

import lombok.Data;

@Data
public class ContactItemDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String birthDay;
}
