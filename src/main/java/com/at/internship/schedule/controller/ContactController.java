package com.at.internship.schedule.controller;

import com.at.internship.schedule.Constants.Error;
import com.at.internship.schedule.Constants.Validation;
import com.at.internship.schedule.converter.ContactConverter;
import com.at.internship.schedule.domain.Contact;
import com.at.internship.schedule.dto.ContactCreateDto;
import com.at.internship.schedule.dto.ContactDetailsDto;
import com.at.internship.schedule.dto.ContactFiltersDto;
import com.at.internship.schedule.dto.ContactUpdateDto;
import com.at.internship.schedule.response.GenericResponse;
import com.at.internship.schedule.service.IContactService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/contact")
@SuppressWarnings("unused")
public class ContactController {

    private final IContactService contactService;
    private final ContactConverter contactConverter;

    public ContactController(IContactService contactService, ContactConverter contactConverter){
        this.contactConverter = contactConverter;
        this.contactService = contactService;
    }

    @GetMapping("/get/{id}")
    public GenericResponse<ContactDetailsDto> getContactById(@PathVariable("id") Integer id){
        GenericResponse<ContactDetailsDto> response = new GenericResponse<>();
        response.setTimestamp(LocalDateTime.now());
        response.setCode(Validation.SUCCESSFUL_ACTION_CODE);
        response.setMessage(Validation.SUCCESSFUL_ACTION_MESSAGE);
        response.setContent(contactConverter.toContactDetailsDto(Objects.requireNonNull(contactService.findById(id).orElse(null))));
        return response;
    }

    @GetMapping("/find")
    public GenericResponse<Page<ContactDetailsDto>> findAll(ContactFiltersDto filters, Pageable pageable){
        GenericResponse<Page<ContactDetailsDto>> response = new GenericResponse<>();

        Page<Contact> page = contactService.findAll(filters, pageable);
        List<ContactDetailsDto> content = page
                .stream()
                .map(contactConverter::toContactDetailsDto)
                .collect(Collectors.toList());

        response.setTimestamp(LocalDateTime.now());
        response.setCode(Validation.SUCCESSFUL_ACTION_CODE);
        response.setMessage(Validation.SUCCESSFUL_ACTION_MESSAGE);
        response.setContent(new PageImpl<>(content, page.getPageable(), page.getTotalElements()));


        return response;
    }


    @PutMapping("/update")
    public GenericResponse<ContactDetailsDto> update(@RequestBody @Valid ContactUpdateDto contactUpdateDto) {
        GenericResponse<ContactDetailsDto> response = new GenericResponse<>();
        response.setTimestamp(LocalDateTime.now());
        response.setCode(Validation.SUCCESSFUL_ACTION_CODE);
        response.setMessage(Validation.SUCCESSFUL_ACTION_MESSAGE);
        response.setContent(contactConverter.toContactDetailsDto(contactService.update(contactConverter.updateContactDtoToContact(contactUpdateDto))));
        return response;
    }

    @PostMapping("/new")
    public GenericResponse<ContactDetailsDto> create(@RequestBody @Valid ContactCreateDto contactCreateDto) {
        GenericResponse<ContactDetailsDto> response = new GenericResponse<>();
        response.setTimestamp(LocalDateTime.now());
        response.setCode(Validation.SUCCESSFUL_ACTION_CODE);
        response.setMessage(Validation.SUCCESSFUL_ACTION_MESSAGE);
        response.setContent(contactConverter.toContactDetailsDto(contactService.create(contactConverter.contactCreateDtoToContact(contactCreateDto))));
        return response;
    }

    @DeleteMapping("/delete/{id}")
    public GenericResponse<?> delete(@PathVariable("id") Integer id) {
        GenericResponse<?> response = new GenericResponse<>();
        response.setTimestamp(LocalDateTime.now());
        response.setCode(Validation.SUCCESSFUL_ACTION_CODE);
        response.setMessage(Validation.SUCCESSFUL_ACTION_MESSAGE);
        contactService.delete(id);
        return response;
    }
}