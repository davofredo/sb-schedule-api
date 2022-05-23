package com.at.internship.schedule.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.at.internship.schedule.converter.ContactConverter;
import com.at.internship.schedule.domain.Contact;
import com.at.internship.schedule.dto.ContactDto;
import com.at.internship.schedule.response.ContactResponse;
import com.at.internship.schedule.response.ErrorResponse;
import com.at.internship.schedule.response.GenericResponse;
import com.at.internship.schedule.service.IContactService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    IContactService contactService;
    ContactConverter contactConverter;

    public ContactController() {

    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Object> listContactsId(@PathVariable("id") Integer id) throws IOException {
        Optional<Contact> contact = contactService.findById(id);
        if (contact.isPresent()) {
            ContactResponse contactResponse = new ContactResponse();
            contactResponse.setCode("OK");
            contactResponse.setMessage("Success!");
            contactResponse.setContent(contact.get());
            return new ResponseEntity<>(contactResponse, HttpStatus.OK);
        } else {
            ErrorResponse erroor = new ErrorResponse();
            erroor.setCode("RECORD_NOT_FOUND");
            erroor.setMessage("Record not found");
            erroor.setContent(null);
            List<String> l = new ArrayList<>();
            l.add("Requested Contact with ID " + id + "was not found");
            erroor.setErrorMessage(l);
            return new ResponseEntity<>(erroor, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/find/{id}/{firstName}/{lastName}/{nameLike}/{emailAddress}/{birtDay}/{birthdayGte}/{birthdayLte}/{page}/{size}/{sort}")
    public List<Contact> findByFirstNameOrderByFirstNameAsc(@PathVariable("id") Integer id,
            @PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName,
            @PathVariable("nameLike") String nameLike, @PathVariable("emailAddress") String emailAddress,
            @PathVariable("birtDay") String birthDay, @PathVariable("birthdayGte") String birthdayGte,
            @PathVariable("birthdayLte") String birthdayLte, @PathVariable("page") Integer page,
            @PathVariable("size") Integer size, @PathVariable("sort") List<String> sort) throws IOException {
        // return contactService.findByFirstNameOrderByFirstNameAsc(nombre);
        return null;
    }

    @PostMapping("/new")
    public ResponseEntity<Object> save(@RequestBody @Valid Contact contact) throws IOException {
        if (!contact.getFirstName().isEmpty() && !contact.getLastName().isEmpty() && contact.getId() > 0
                && !contact.getEmailAddress().isEmpty() && !contact.getBirthDay().toString().isEmpty()) {
            ContactResponse contactResponse = new ContactResponse();
            contactService.save(contact);
            contactResponse.setCode("OK");
            contactResponse.setMessage("Success!");
            contactResponse.setContent(contact);
            return new ResponseEntity<>(contactResponse, HttpStatus.OK);
        }
        if (contactService.findByEmailAddressNotLike(contact.getEmailAddress()).isEmpty()) {
            ErrorResponse error = new ErrorResponse();
            error.setCode("DB_CONSTRAINT_VIOLATION");
            error.setMessage("Database constraint violation");
            error.setContent(null);
            List<String> l = new ArrayList<>();
            l.add("The email " + contact.getEmailAddress() + " is already taken by another contact");
            error.setErrorMessage(l);
            return new ResponseEntity<>(error, HttpStatus.CONFLICT);
        }
        ErrorResponse error = new ErrorResponse();
        error.setCode("VALIDATION_FAILED");
        error.setMessage("There were valdiations errors");
        error.setContent(null);
        List<String> l = new ArrayList<>();
        l.add("First name should be at least 2 characters length and 255 maximum");
        l.add("First name is requierd");
        l.add("Please provide a valid email");
        error.setErrorMessage(l);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> update(@RequestBody @Valid Contact contact) {
        if (contactService.findById(contact.getId()).isPresent()) {
            ContactResponse contactResponse = new ContactResponse();
            contactResponse.setCode("OK");
            contactResponse.setMessage("Success!");
            contactResponse.setContent(contact);
            return new ResponseEntity<>(contactResponse, HttpStatus.OK);
        }
        if (contact.getFirstName().length() < 2 || contact.getFirstName().length() > 255
                || contact.getFirstName().isEmpty() || !contact.getEmailAddress().contains("@")) {
            ErrorResponse error = new ErrorResponse();
            error.setCode("VALIDATION_FAILED");
            error.setMessage("There were valdiations errors");
            error.setContent(null);
            List<String> l = new ArrayList<>();
            l.add("First name should be at least 2 characters length and 255 maximum");
            l.add("First name is requierd");
            l.add("Please provide a valid email address");
            error.setErrorMessage(l);
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        if (contactService.findByEmailAddressNotLike(contact.getEmailAddress()).isEmpty()) {
            ErrorResponse error = new ErrorResponse();
            error.setCode("DB_CONSTRAINT_VIOLATION");
            error.setMessage("Database constraint violation");
            error.setContent(null);
            List<String> l = new ArrayList<>();
            l.add("The email " + contact.getEmailAddress() + " is already taken by another contact");
            error.setErrorMessage(l);
            return new ResponseEntity<>(error, HttpStatus.CONFLICT);
        }
        ErrorResponse error = new ErrorResponse();
        error.setCode("RECORD_NOT_FOUND");
        error.setMessage("Record not found");
        error.setContent(null);
        List<String> l = new ArrayList<>();
        l.add("Requested Contact with ID " + contact.getId().toString() + " was not found");
        error.setErrorMessage(l);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Integer id) throws IOException {
        Optional<Contact> contact = contactService.findById(id);
        if (contactService.findById(id).isEmpty()) {
            ErrorResponse erroor = new ErrorResponse();
            erroor.setCode("RECORD_NOT_FOUND");
            erroor.setMessage("Record not found");
            erroor.setContent(null);
            List<String> l = new ArrayList<>();
            l.add("Requested Contact with ID " + id + "was not found");
            erroor.setErrorMessage(l);
            return new ResponseEntity<>(erroor, HttpStatus.NOT_FOUND);
        } else {
            contactService.delete(id);
            Optional<Contact> contact1 = contactService.findById(id);
            if (contact1.isEmpty()) {
                ContactResponse contactResponse = new ContactResponse();
                contactResponse.setCode("OK");
                contactResponse.setMessage("Success!");
                contactResponse.setContent(contact.get());
                return new ResponseEntity<>(contactResponse, HttpStatus.OK);
            }
        }
        ErrorResponse erroor = new ErrorResponse();
        erroor.setCode("VALIDATION_FAILED");
        erroor.setMessage("There were validation erros");
        erroor.setContent(null);
        List<String> l = new ArrayList<>();
        l.add("First name should be at least 2 characters length and 255 maximum");
        l.add("First name is requierd");
        l.add("Please provide a valid email address");
        erroor.setErrorMessage(l);
        return new ResponseEntity<>(erroor, HttpStatus.NOT_FOUND);

    }
}
