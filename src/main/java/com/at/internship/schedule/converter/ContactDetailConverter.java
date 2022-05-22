package com.at.internship.schedule.converter;

import com.at.internship.schedule.domain.Contact;
import com.at.internship.schedule.dto.ContactDetailDto;
import com.at.internship.schedule.utils.DateUtils;
import org.springframework.stereotype.Component;

@Component
public class ContactDetailConverter {
    private final DateUtils dateUtils;

    public ContactDetailConverter(DateUtils dateUtils) {
        this.dateUtils = dateUtils;
    }

    public ContactDetailDto toContactCreatedDto(Contact contact) {
        ContactDetailDto contactCreatedDto = new ContactDetailDto();
        contactCreatedDto.setId(contact.getId());
        contactCreatedDto.setFirstName(contact.getFirstName());
        contactCreatedDto.setLastName(contact.getLastName());
        contactCreatedDto.setEmailAddress(contact.getEmailAddress());
        contactCreatedDto.setBirthDay(dateUtils.formatDefault(contact.getBirthDay()));
        return contactCreatedDto;
    }
}
