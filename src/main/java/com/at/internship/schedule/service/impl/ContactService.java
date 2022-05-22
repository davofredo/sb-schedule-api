package com.at.internship.schedule.service.impl;

import com.at.internship.schedule.domain.Contact;
import com.at.internship.schedule.dto.ContactFiltersDto;
import com.at.internship.schedule.repository.ContactRepository;
import com.at.internship.schedule.service.IContactService;
import com.at.internship.schedule.util.helper.ContactSpecificationsHelper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class ContactService implements IContactService {

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public Page<Contact> findAll(ContactFiltersDto filters, Pageable pageable) {
        return contactRepository.findAll(ContactSpecificationsHelper.getContactSpecs(filters),pageable);
    }

    @Override
    public Contact findOne(Integer id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> {throw new EntityNotFoundException(String.format("Requested Contact with ID %s was not found", id));});
    }

    @Override
    public Contact save(Contact contact) {
        if(contactRepository.existsByEmailAddress(contact.getEmailAddress()))
            throw new DataIntegrityViolationException(String.format("The email %s is already taken by another contact", contact.getEmailAddress()));
        return contactRepository.save(contact);
    }

    @Override
    public Contact delete(Integer id) {
        Contact contact = this.findOne(id);
        contactRepository.delete(contact);
        return contact;
    }
}
