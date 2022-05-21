package com.at.internship.schedule.controller;

import com.at.internship.schedule.converter.ContactConverter;
import com.at.internship.schedule.domain.Contact;
import com.at.internship.schedule.dto.ContactDto;
import com.at.internship.schedule.exception.custom.CustomResponseEntity;
import com.at.internship.schedule.service.impl.ContactServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/contact")
public class ContactController {

    private final ContactServiceImpl contactService;
    private final ContactConverter contactConverter;

    public ContactController(ContactServiceImpl contactService, ContactConverter contactConverter) {
        this.contactService = contactService;
        this.contactConverter = contactConverter;
    }

    @GetMapping("/find")
    public ResponseEntity<List<ContactDto>> find() {
        List<ContactDto> contactDtoList =
            contactService.findAll().stream().map(contactConverter::toContactDto).collect(Collectors.toList());
        return ResponseEntity.ok(contactDtoList);
    }

    @PostMapping(value = "/new")
    public ResponseEntity<?> newContact(@Valid @RequestBody Contact newContact) {
        Contact contact = contactService.create(newContact);
        ContactDto contactDto = contactConverter.toContactDto(contact);
        CustomResponseEntity cre = new CustomResponseEntity(
            LocalDateTime.now(), HttpStatus.OK,
            "Success!", contactDto
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
        ContactDto contactDto =contactConverter.toContactDto(deletedContact);
        CustomResponseEntity cre = new CustomResponseEntity(
            LocalDateTime.now(), HttpStatus.OK,
            "Success!", contactDto
        );
        return new ResponseEntity<>(cre, HttpStatus.OK);
    }
}
