package com.at.internship.schedule.controller;

import com.at.internship.schedule.converter.ContactConverter;
import com.at.internship.schedule.domain.Contact;
import com.at.internship.schedule.dto.ContactCreateDto;
import com.at.internship.schedule.dto.ContactDetailsDto;
import com.at.internship.schedule.dto.ContactFiltersDto;
import com.at.internship.schedule.dto.ContactUpdateDto;
import com.at.internship.schedule.service.IContactService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ContactDetailsDto getContactById(@PathVariable("id") Integer id){
        return contactConverter.toContactDetailsDto(Objects.requireNonNull(contactService.findById(id).orElse(null)));
    }

    @GetMapping("/find")
    public Page<ContactDetailsDto> findAll(ContactFiltersDto filters, Pageable pageable){
        Page<Contact> page = contactService.findAll(filters, pageable);
        List<ContactDetailsDto> content = page
                .stream()
                .map(contactConverter::toContactDetailsDto)
                .collect(Collectors.toList());
        return new PageImpl<>(content, page.getPageable(), page.getTotalElements());
    }


    @PutMapping("/update")
    public ContactDetailsDto update(@RequestBody @Valid ContactUpdateDto contactUpdateDto) {
        return contactConverter.toContactDetailsDto(contactService.update(contactConverter.updateContactDtoToContact(contactUpdateDto)));
    }

    @PostMapping("/new")
    public ContactDetailsDto create(@RequestBody @Valid ContactCreateDto contactCreateDto) {
        return contactConverter.toContactDetailsDto(contactService.create(contactConverter.contactCreateDtoToContact(contactCreateDto)));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Integer id) {
        contactService.delete(id);
    }
}