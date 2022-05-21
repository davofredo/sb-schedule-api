package com.at.internship.schedule.controller;

import com.at.internship.schedule.constants.AppConstants;
import com.at.internship.schedule.converter.ModelMapperConverter;
import com.at.internship.schedule.domain.Contact;
import com.at.internship.schedule.dto.ContactCreateDto;
import com.at.internship.schedule.dto.ContactDetailsDto;
import com.at.internship.schedule.dto.ContactItemDto;
import com.at.internship.schedule.dto.ContactUpdateDto;
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
    private final ModelMapperConverter modelMapperConverter;

    public ContactController(IContactService contactService, ModelMapperConverter modelMapperConverter) {
        this.contactService = contactService;
        this.modelMapperConverter = modelMapperConverter;
    }

    @GetMapping("/find")
    public PageResponse<ContactItemDto> findAll(Pageable pageable) {
        Page<Contact> page = contactService.findAll(pageable);
        List<ContactItemDto> contacts = modelMapperConverter.convertListTo(page.getContent(), ContactItemDto.class);
        return new PageResponse<>(
                LocalDateTime.now(), AppConstants.STR_CODE_OK, AppConstants.STR_MESSAGE_SUCCESS, contacts,
                page.getNumber(),page.getSize(),page.getTotalPages(),
                page.getTotalElements(),page.getNumberOfElements(),
                page.isFirst(),page.isLast(),page.isEmpty());
    }

    @GetMapping("/get/{id}")
    public GenericResponse<ContactDetailsDto> findOne(@PathVariable Integer id){
        return new GenericResponse<>(
                LocalDateTime.now(), AppConstants.STR_CODE_OK, AppConstants.STR_MESSAGE_SUCCESS,
                modelMapperConverter.convertTo(contactService.findOne(id), ContactDetailsDto.class));
    }

    @PostMapping("/new")
    public GenericResponse<ContactDetailsDto> save(@RequestBody @Valid ContactCreateDto contact) {
        return new GenericResponse<>(
                LocalDateTime.now(), AppConstants.STR_CODE_OK, AppConstants.STR_MESSAGE_SUCCESS,
                modelMapperConverter.convertTo(
                        contactService.save(modelMapperConverter.convertTo(contact, Contact.class)),
                        ContactDetailsDto.class));
    }

    @PutMapping("/update")
    public GenericResponse<ContactDetailsDto> update(@RequestBody @Valid ContactUpdateDto contact) {
        return new GenericResponse<>(
                LocalDateTime.now(), AppConstants.STR_CODE_OK, AppConstants.STR_MESSAGE_SUCCESS,
                modelMapperConverter.convertTo(
                        contactService.save(modelMapperConverter.convertTo(contact, Contact.class)),
                        ContactDetailsDto.class));
    }

    @DeleteMapping("/delete/{id}")
    public GenericResponse<ContactDetailsDto> delete(@PathVariable Integer id){
        return new GenericResponse<>(
                LocalDateTime.now(), AppConstants.STR_CODE_OK, AppConstants.STR_MESSAGE_SUCCESS,
                modelMapperConverter.convertTo(contactService.delete(id), ContactDetailsDto.class));
    }

}
