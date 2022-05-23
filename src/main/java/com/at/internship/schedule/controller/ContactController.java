package com.at.internship.schedule.controller;

import com.at.internship.schedule.domain.Contact;
import com.at.internship.schedule.dto.ContactCreateDto;
import com.at.internship.schedule.dto.ContactUpdateDto;
import com.at.internship.schedule.dto.GenericResponseContactDetailsDto;
import com.at.internship.schedule.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(path = "/contact")
public class ContactController {

    @Autowired
    ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService){
        this.contactService = contactService;
    }

    @PutMapping(value = "/update")
    public GenericResponseContactDetailsDto updateContact(@RequestBody ContactUpdateDto contactUpdateDto){

        return contactService.updateContact(contactUpdateDto);
    }

    @PostMapping(value = "/new")
    public GenericResponseContactDetailsDto registerNewContact(@RequestBody ContactCreateDto contactCreateDto) {
        return contactService.addNewContact(contactCreateDto);
    }

    @GetMapping(value = "/get/{contactId}")
    public GenericResponseContactDetailsDto getContactById(@PathVariable("contactId") Integer contactId) {
        return contactService.getById(contactId);
    }
/*
    @GetMapping(value = "/find/{id,firstName,lastName,nameLike,emailAddress,birthDay,birthDayGte,birthDayLte,page,size,sort}")
    public GenericResponseContactDetailsDto getContact(@PathParam("id") String contactId,
                                                       @PathParam("firstName") String firstName,
                                                       @PathParam("lastName") String lastName,
                                                       @PathParam("nameLike") String nameLike,
                                                       @PathParam("emailAddress") String emailAddress,
                                                       @PathParam("birthDay") String birthDay,
                                                       @PathParam("birthDayGte") String birthDayGte,
                                                       @PathParam("birthDayLte") String birthDayLte,
                                                       @PathParam("page") String page,
                                                       @PathParam("size") String size,
                                                       @PathParam("sort") String sort){
        System.out.println("LLegamos a getContact");
        return null;
    }
*/

    @DeleteMapping(path = "/delete/{contactId}")
    public GenericResponseContactDetailsDto deleteContact(@PathVariable("contactId") Integer contactId) {
        return contactService.deleteContact(contactId);

    }
}