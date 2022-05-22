package com.at.internship.schedule.converter;

import com.at.internship.schedule.domain.Contact;
import com.at.internship.schedule.dto.ContactCreatedDto;
import com.at.internship.schedule.utils.DateUtils;
import org.springframework.stereotype.Component;

@Component
public class ContactCreatedConverter {
    private final DateUtils dateUtils;

    public ContactCreatedConverter(DateUtils dateUtils) {
        this.dateUtils = dateUtils;
    }

    public ContactCreatedDto toContactCreatedDto(Contact contact) {
        ContactCreatedDto contactCreatedDto = new ContactCreatedDto();
        contactCreatedDto.setId(contact.getId());
        contactCreatedDto.setFirstName(contact.getFirstName());
        contactCreatedDto.setLastName(contact.getLastName());
        contactCreatedDto.setEmailAddress(contact.getEmailAddress());
        contactCreatedDto.setBirthDay(dateUtils.formatDefault(contact.getBirthDay()));
        return contactCreatedDto;
    }
}
