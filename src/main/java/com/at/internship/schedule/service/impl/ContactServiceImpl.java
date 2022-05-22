package com.at.internship.schedule.service.impl;

import java.util.List;
import java.util.Optional;

import com.at.internship.schedule.domain.Contact;
import com.at.internship.schedule.dto.ContactFiltersDto;
import com.at.internship.schedule.repository.IContactRepository;
import com.at.internship.schedule.service.IContactService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements IContactService {

    @Autowired
    IContactRepository contactRepository;

    @Override
    public Page<Contact> findAll(ContactFiltersDto filters, Pageable pageable) {

        return null;
    }

    @Override
    public void save(Contact contact) {
        contactRepository.save(contact);
        
    }

    @Override
    public Optional<Contact> findById(Integer id) {
        return contactRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        contactRepository.findById(id);
    }

    @Override
    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    @Override
    public List<Contact> findByFirstNameOrderByFirstNameAsc(String firstName) {
        return contactRepository.findByFirstNameOrderByFirstNameAsc(firstName);
    }

    @Override
    public List<Contact> findByFirstNameNotLike(String firstName) {
        return contactRepository.findByFirstNameNotLike(firstName);
    }
    
}
