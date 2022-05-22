package com.at.internship.schedule.converter;

import com.at.internship.schedule.domain.ContactPhone;
import com.at.internship.schedule.dto.ContactPhoneDto;

import java.util.ArrayList;
import java.util.List;

public class ContactPhoneConverter {

    public ContactPhoneDto toContactPhoneDto(ContactPhone contactPhone) {
        ContactPhoneDto contactPhoneDto = new ContactPhoneDto();
        contactPhoneDto.setId(contactPhone.getId());
        contactPhoneDto.setContactId(contactPhone.getContact().getId());
        contactPhoneDto.setPhoneNumber(contactPhone.getPhoneNumber());
        contactPhoneDto.setLabel(contactPhone.getLabel());
        return contactPhoneDto;
    }

    public List<ContactPhoneDto> transform(List<ContactPhone> contactPhones) {
        List<ContactPhoneDto> contactPhoneDtoList = new ArrayList<>();
        for (ContactPhone contactPhone : contactPhones) {
            contactPhoneDtoList.add( toContactPhoneDto(contactPhone) );
        }
        return contactPhoneDtoList;
    }

}
