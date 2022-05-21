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
        return contactDto;
    }
}
