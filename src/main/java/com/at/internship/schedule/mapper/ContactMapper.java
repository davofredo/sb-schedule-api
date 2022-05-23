package com.at.internship.schedule.mapper;

import com.at.internship.schedule.domain.Contact;
import com.at.internship.schedule.dto.ContactCreateDto;
import com.at.internship.schedule.dto.ContactDetailsDto;
import com.at.internship.schedule.dto.ContactItemDto;
import com.at.internship.schedule.dto.ContactUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ContactMapper {

    @Mappings(value = {
            @Mapping(source = "birthDay", target = "birthDay",
                        dateFormat = "MM/dd/yyyy")
    })
    ContactDetailsDto convertToContactDetailsDto(Contact contact);

    @Mappings(value = {
            @Mapping(source = "birthDay", target = "birthDay",
                    dateFormat = "MM/dd/yyyy")
    })
    ContactItemDto convertToContactItemDto(Contact contact);
    List<ContactItemDto> convertToContactsItemDto(List<Contact> contacts);

    @Mappings(value = {
            @Mapping(source = "contactDto.birthDay", target = "birthDay",
                    dateFormat = "MM/dd/yyyy")
    })
     Contact convertToContact(ContactCreateDto contactDto);

    @Mappings(value = {
            @Mapping(source = "contactDto.birthDay", target = "birthDay",
                    dateFormat = "MM/dd/yyyy")
    })
     Contact convertToContact(ContactUpdateDto contactDto);
}
