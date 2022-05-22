package com.at.internship.schedule.controller;

import com.at.internship.schedule.converter.ContactConverter;
import com.at.internship.schedule.converter.ContactDetailConverter;
import com.at.internship.schedule.domain.Contact;
import com.at.internship.schedule.dto.ContactDetailDto;
import com.at.internship.schedule.dto.ContactDto;
import com.at.internship.schedule.dto.ContactFilterDto;
import com.at.internship.schedule.exception.custom.CustomResponseEntity;
import com.at.internship.schedule.service.impl.ContactServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/contact")
public class ContactController {

    private final ContactServiceImpl contactService;
    private final ContactConverter contactConverter;
    private final ContactDetailConverter contactCreatedConverter;

    public ContactController(ContactServiceImpl contactService, ContactConverter contactConverter, ContactDetailConverter contactCreatedConverter) {
        this.contactService = contactService;
        this.contactConverter = contactConverter;
        this.contactCreatedConverter = contactCreatedConverter;
    }

    @GetMapping("/find")
    public Page<ContactDto> findAll(
        ContactFilterDto filters, Pageable pageable
    ) {
        Page<Contact> page = contactService.findAll(filters, pageable);

        List<ContactDto> content = page
            .stream()
            .map(contactConverter::toContactDto)
            .collect(Collectors.toList());

        return new PageImpl<>(content, page.getPageable(), page.getTotalElements());

    }

    @PostMapping(value = "/new")
    public ResponseEntity<?> newContact(@Valid @RequestBody Contact newContact) {
        Contact contact = contactService.create(newContact);
        ContactDetailDto contactDetailDto = contactCreatedConverter.toContactCreatedDto(contact);
        CustomResponseEntity cre = new CustomResponseEntity(
            LocalDateTime.now(), HttpStatus.OK,
            "Success!", contactDetailDto
        );
        return new ResponseEntity<>(cre, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateContact(@Valid @RequestBody Contact contact) {
        Contact updatedContact = contactService.update(contact);
        ContactDto contactDto = contactConverter.toContactDto(updatedContact);
        CustomResponseEntity cre = new CustomResponseEntity(
            LocalDateTime.now(), HttpStatus.OK,
            "Success!", contactDto
        );
        return new ResponseEntity<>(cre, HttpStatus.OK);
    }

    @DeleteMapping(path = "/delete")
    private ResponseEntity<?> deleteContact(@RequestParam Integer id) {
        Contact deletedContact = contactService.deleteContact(id);
        ContactDetailDto contactDetailDto = contactCreatedConverter.toContactCreatedDto(deletedContact);
        CustomResponseEntity cre = new CustomResponseEntity(
            LocalDateTime.now(), HttpStatus.OK,
            "Success!", contactDetailDto
        );
        return new ResponseEntity<>(cre, HttpStatus.OK);
    }

    @GetMapping("/get/{contactId}")
    private ResponseEntity<?> getContact(@PathVariable Integer contactId) {
        Contact contact = contactService.getContact(contactId);
        ContactDto contactDto = contactConverter.toContactDto(contact);
        CustomResponseEntity cre = new CustomResponseEntity(
            LocalDateTime.now(), HttpStatus.OK,
            "Success!", contactDto
        );
        return new ResponseEntity<>(cre, HttpStatus.OK);
    }
}
