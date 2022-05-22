package com.at.internship.schedule.dto;

import lombok.Data;


@Data
public class ContactDetailsDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String birthDay;
    //private List<String> contactPhones;
}
