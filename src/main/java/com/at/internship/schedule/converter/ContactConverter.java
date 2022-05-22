package com.at.internship.schedule.converter;

import com.at.internship.schedule.domain.Contact;
import com.at.internship.schedule.dto.ContactDetailsDto;
import com.at.internship.schedule.dto.ContactCreateDto;
import com.at.internship.schedule.dto.ContactUpdateDto;
import com.at.internship.schedule.utils.DateUtils;
import org.springframework.stereotype.Component;

@Component
public class ContactConverter {

    private final DateUtils dateUtils;

    public ContactConverter(DateUtils dateUtils) {
        this.dateUtils = dateUtils;
    }

    public ContactDetailsDto toContactDetailsDto(Contact contact) {
        ContactDetailsDto contactDetailsDto = new ContactDetailsDto();
        contactDetailsDto.setId(contact.getId());
        contactDetailsDto.setFirstName(contact.getFirstName());
        contactDetailsDto.setLastName(contact.getLastName());
        contactDetailsDto.setEmailAddress(contact.getEmailAddress());
        contactDetailsDto.setBirthDay(dateUtils.formatDefault(contact.getBirthDay()));
        return contactDetailsDto;
    }

    public Contact updateContactDtoToContact(ContactUpdateDto contactUpdateDto) {
        Contact contact = new Contact();
        contact.setId(contactUpdateDto.getId());
        contact.setFirstName(contactUpdateDto.getFirstName());
        contact.setLastName(contactUpdateDto.getLastName());
        contact.setEmailAddress(contactUpdateDto.getEmailAddress());
        contact.setBirthDay(dateUtils.parseDefaultDate(contactUpdateDto.getBirthDay()));
        return contact;
    }


    public Contact contactCreateDtoToContact(ContactCreateDto contactCreateDtoDto) {
        Contact contact = new Contact();
        contact.setFirstName(contactCreateDtoDto.getFirstName());
        contact.setLastName(contactCreateDtoDto.getLastName());
        contact.setEmailAddress(contactCreateDtoDto.getEmailAddress());
        contact.setBirthDay(dateUtils.parseDefaultDate(contactCreateDtoDto.getBirthDay()));
        return contact;
    }


}