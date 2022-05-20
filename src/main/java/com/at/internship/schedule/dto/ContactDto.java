package com.at.internship.schedule.dto;

import java.time.LocalDate;
import java.util.List;

import com.at.internship.schedule.domain.Appointment;
import com.at.internship.schedule.domain.ContactPhone;

import lombok.Data;

@Data
public class ContactDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String birthDay;
    private List<ContactPhone> contactPhones;
    private List<Appointment> appointments;
}
