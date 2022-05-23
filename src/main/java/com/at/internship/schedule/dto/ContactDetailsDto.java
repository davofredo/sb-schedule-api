package com.at.internship.schedule.dto;

import com.at.internship.schedule.domain.Contact;
import lombok.Data;

import java.util.List;
@Data
public class ContactDetailsDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String birthDay;
    //private List<ContactPhoneDto> contactPhones;

    public ContactDetailsDto(Contact contact){
        this.id = contact.getId();
        this.firstName = contact.getFirstName();
        this.lastName = contact.getLastName();
        this.emailAddress = contact.getEmailAddress();
        this.birthDay = contact.getBirthDay().toString();
    }
}
