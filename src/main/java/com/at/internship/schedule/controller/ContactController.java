package com.at.internship.schedule.controller;

import java.util.List;
import java.util.Optional;

import com.at.internship.schedule.domain.Contact;
import com.at.internship.schedule.service.IContactService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    IContactService contactService;

    public ContactController () {

    }

    @GetMapping("/findAll")
    public List<Contact> listContacts() {
        return contactService.findAll();
    }

    @GetMapping("/findById/{id}")
    public Optional<Contact> listContactsId(@PathVariable("id") Integer id) {
        return contactService.findById(id);
    }

    @GetMapping("/findByNombreOrderByNombreAsc/{nombre}")
    public List<Contact> findByNombreOrderByNombreAsc(@PathVariable("nombre") String nombre) {
        return contactService.findByNombreOrderByNombreAsc(nombre);
    }

    @PostMapping("/save")
    public void save(@RequestBody Contact contact) {
        // Validar que actualiza o crea
        Optional<Contact> contactF = contactService.findById(contact.getId());
        if (contactF.isEmpty())
            contactService.save(contact);
    }

    @PutMapping("/update")
    public void update(@RequestBody Contact contact) {
            contactService.save(contact);
    }

    @DeleteMapping("/delete/{id]")
    public void delete(@PathVariable("id") Integer id) {
        contactService.delete(id);
    }
}
