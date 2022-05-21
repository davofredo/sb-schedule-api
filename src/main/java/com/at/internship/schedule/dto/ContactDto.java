package com.at.internship.schedule.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ContactDto implements Serializable {
    private Integer id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    private String birthDay;
}