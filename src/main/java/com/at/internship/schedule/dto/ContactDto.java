package com.at.internship.schedule.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.at.internship.schedule.domain.Appointment;
import com.at.internship.schedule.domain.ContactPhone;


import lombok.Data;

@Data
public class ContactDto {
    private Integer id;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String emailAddress;
    @NotNull
    private String birthDay;
    @NotNull
    private String contactPhones;
    @NotNull
    private String appointments;
}
