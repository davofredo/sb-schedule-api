package com.at.internship.schedule.service;

import com.at.internship.schedule.domain.Contact;
import com.at.internship.schedule.dto.ContactFiltersDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface IContactService {
    Page<Contact> findAll(ContactFiltersDto filters, Pageable pageable);
    Contact create(Contact contact);
    Contact update(Contact contact);
    Optional<Contact> findById(Integer id);
    void delete(Integer id);
}