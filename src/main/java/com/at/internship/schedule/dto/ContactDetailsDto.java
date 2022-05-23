package com.at.internship.schedule.dto;

import lombok.Data;

import java.util.List;

@Data
public class ContactDetailsDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String birthDay;
    private List<ContactPhoneDto> contactPhones;
}
