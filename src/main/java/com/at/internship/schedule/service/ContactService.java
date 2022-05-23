package com.at.internship.schedule.service;

import com.at.internship.schedule.domain.Contact;
import com.at.internship.schedule.dto.*;
import com.at.internship.schedule.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Service
public class ContactService {
    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public GenericResponseContactDetailsDto addNewContact(ContactCreateDto contact){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Contact contacto = new Contact();
        contacto.setFirstName(contact.getFirstName());
        contacto.setLastName(contact.getLastName());
        contacto.setEmailAddress(contact.getEmailAddress());
        contacto.setBirthDay(LocalDate.parse(contact.getBirthDay(), formatter));
        Contact objeto = new Contact();
        try {
            objeto = contactRepository.save(contacto);
        }catch (Exception e){
            System.out.println(e);
        }

        ContactDetailsDto contactDto = new ContactDetailsDto(objeto);
        return new GenericResponseContactDetailsDto(LocalDate.now().toString(), "OK", "Success", contactDto);
    }

    public GenericResponseContactDetailsDto deleteContact(Integer contactId) {
        Contact objeto = contactRepository.getById(contactId);
        ContactDetailsDto contactDto = new ContactDetailsDto(objeto);
        contactRepository.deleteById(contactId);
        return new GenericResponseContactDetailsDto(LocalDate.now().toString(), "OK", "Success", contactDto);
    }

    public GenericResponseContactDetailsDto getById(Integer contactId) {
        Contact objeto = contactRepository.getById(contactId);
        ContactDetailsDto contactDto = new ContactDetailsDto(objeto);
        return new GenericResponseContactDetailsDto(LocalDate.now().toString(), "OK", "Success", contactDto);
    }

    public GenericResponseContactDetailsDto updateContact(ContactUpdateDto contactUpdateDto) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Contact contacto = contactRepository.getById(contactUpdateDto.getId());
        contacto.setFirstName(contactUpdateDto.getFirstName());
        contacto.setLastName(contactUpdateDto.getLastName());
        contacto.setEmailAddress(contactUpdateDto.getEmailAddress());
        contacto.setBirthDay(LocalDate.parse(contactUpdateDto.getBirthDay(),formatter));
        Contact objeto = new Contact();
        try {
            objeto = contactRepository.save(contacto);
        }catch (Exception e){
            System.out.println(e);
        }

        ContactDetailsDto contactDto = new ContactDetailsDto(objeto);
        return new GenericResponseContactDetailsDto(LocalDate.now().toString(), "OK", "Success", contactDto);

    }
}
