package com.at.internship.schedule.service.impl;

import com.at.internship.schedule.domain.Contact;
import com.at.internship.schedule.repository.ContactRepository;
import com.at.internship.schedule.service.IContactService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ContactService implements IContactService {

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    @Override
    public Contact findOne(Integer id) {
        return contactRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }

    @Override
    public Contact delete(Integer id) {
        Contact contact = this.findOne(id);
        contactRepository.delete(contact);
        return contact;
    }
}
