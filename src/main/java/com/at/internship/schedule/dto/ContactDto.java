package com.at.internship.schedule.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ContactDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private LocalDate birthDay;
}
