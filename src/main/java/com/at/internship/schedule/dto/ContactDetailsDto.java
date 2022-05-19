package com.at.internship.schedule.dto;

import com.at.internship.schedule.domain.ContactPhone;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ContactDetailsDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private LocalDate birthDay;
    private List<ContactPhone> contactPhones;
}
