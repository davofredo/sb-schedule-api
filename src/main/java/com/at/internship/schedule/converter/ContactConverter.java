package com.at.internship.schedule.converter;

import com.at.internship.schedule.domain.Contact;
import com.at.internship.schedule.dto.ContactDto;
import com.at.internship.schedule.utils.DateUtils;

import org.springframework.stereotype.Component;

@Component
public class ContactConverter {

    private final DateUtils dateUtils;

    public ContactConverter(DateUtils dateUtils) {
        this.dateUtils = dateUtils;
    }

    public ContactDto toContactDto(Contact contact) {
        ContactDto contactDto = new ContactDto();
        contactDto.setId(contact.getId());
        contactDto.setFirstName(contact.getFirstName());
        contactDto.setLastName(contact.getLastName());
        contactDto.setEmailAddress(contact.getEmailAddress());
        contactDto.setBirthDay(dateUtils.formatDefault(contact.getBirthDay()));
        //contactDto.setContactPhones(contact.getContactPhones());
        //contactDto.setAppointments(contact.getAppointments());
        return contactDto;
    }

    public Contact toContact(ContactDto contactDto) {
        Contact contact = new Contact();
        contact.setId(contactDto.getId());
        contact.setFirstName(contactDto.getFirstName());
        contact.setLastName(contactDto.getLastName());
        contact.setEmailAddress(contactDto.getEmailAddress());
        contact.setBirthDay(dateUtils.parseDefaultDate(contactDto.getBirthDay()));
        //contact.setContactPhones(contactDto.getContactPhones());
        //contact.setAppointments(contactDto.getAppointments());
        return contact;
    }

}