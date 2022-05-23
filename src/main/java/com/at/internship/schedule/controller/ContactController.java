package com.at.internship.schedule.controller;

import com.at.internship.schedule.constants.MessageConstants;
import com.at.internship.schedule.domain.Contact;
import com.at.internship.schedule.dto.*;
import com.at.internship.schedule.mapper.ContactMapper;
import com.at.internship.schedule.response.GenericResponse;
import com.at.internship.schedule.response.PageResponse;
import com.at.internship.schedule.service.IContactService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/contact")
public class ContactController {

    private final IContactService contactService;
    private final ContactMapper contactMapper;



    public ContactController(IContactService contactService, ContactMapper contactMapper) {
        this.contactService = contactService;
        this.contactMapper = contactMapper;
    }

    @GetMapping("/find")
    public PageResponse<ContactItemDto> findAll(ContactFiltersDto filters, Pageable pageable) {
        Page<Contact> page = contactService.findAll(filters,pageable);
        List<ContactItemDto> contacts = contactMapper.convertToContactsItemDto(page.getContent());
        return new PageResponse<>(
                LocalDateTime.now(), MessageConstants.STR_CODE_OK, MessageConstants.STR_MESSAGE_SUCCESS, contacts,
                page.getNumber(),page.getSize(),page.getTotalPages(),
                page.getTotalElements(),page.getNumberOfElements(),
                page.isFirst(),page.isLast(),page.isEmpty());
    }

    @GetMapping("/get/{id}")
    public GenericResponse<ContactDetailsDto> findOne(@PathVariable Integer id){
        return new GenericResponse<>(
                LocalDateTime.now(), MessageConstants.STR_CODE_OK, MessageConstants.STR_MESSAGE_SUCCESS,
                contactMapper.convertToContactDetailsDto(contactService.findOne(id)));
    }

    @PostMapping("/new")
    public GenericResponse<ContactDetailsDto> save(@RequestBody @Valid ContactCreateDto contact) {
        return new GenericResponse<>(
                LocalDateTime.now(), MessageConstants.STR_CODE_OK, MessageConstants.STR_MESSAGE_SUCCESS,
                contactMapper.convertToContactDetailsDto(contactService.save(contactMapper.convertToContact(contact))));
    }

    @PutMapping("/update")
    public GenericResponse<ContactDetailsDto> update(@RequestBody @Valid ContactUpdateDto contact) {
        return new GenericResponse<>(
                LocalDateTime.now(), MessageConstants.STR_CODE_OK, MessageConstants.STR_MESSAGE_SUCCESS,
                contactMapper.convertToContactDetailsDto(contactService.save(contactMapper.convertToContact(contact))));
    }

    @DeleteMapping("/delete/{id}")
    public GenericResponse<ContactDetailsDto> delete(@PathVariable Integer id, @RequestParam(required = false) boolean force){
        return new GenericResponse<>(
                LocalDateTime.now(), MessageConstants.STR_CODE_OK, MessageConstants.STR_MESSAGE_SUCCESS,
                contactMapper.convertToContactDetailsDto(contactService.delete(id, force)));
    }

}
