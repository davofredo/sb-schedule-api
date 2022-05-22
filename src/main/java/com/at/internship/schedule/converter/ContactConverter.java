package com.at.internship.schedule.converter;

import com.at.internship.schedule.domain.Contact;
import com.at.internship.schedule.domain.ContactPhone;
import com.at.internship.schedule.dto.ContactDto;
import com.at.internship.schedule.dto.ContactPhoneDto;
import com.at.internship.schedule.utils.DateUtils;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ContactConverter {
    private final DateUtils dateUtils;

    public ContactConverter(DateUtils dateUtils) {
        this.dateUtils = dateUtils;
    }

    public ContactDto toContactDto(Contact contact) {
        ContactDto contactDto = new ContactDto();

        ContactPhoneConverter contactPhoneConverter = new ContactPhoneConverter();
        List<ContactPhoneDto> contactPhoneDtoList = contactPhoneConverter.transform(contact.getContactPhones());

        contactDto.setId(contact.getId());
        contactDto.setFirstName(contact.getFirstName());
        contactDto.setLastName(contact.getLastName());
        contactDto.setEmailAddress(contact.getEmailAddress());
        contactDto.setBirthDay(dateUtils.formatDefault(contact.getBirthDay()));
        contactDto.setPhoneNumbers(contactPhoneDtoList);
        return contactDto;
    }
}
