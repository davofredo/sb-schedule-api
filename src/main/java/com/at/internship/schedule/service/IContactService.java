package com.at.internship.schedule.service;

import java.util.List;
import java.util.Optional;

import com.at.internship.schedule.domain.Contact;
import com.at.internship.schedule.dto.ContactFiltersDto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IContactService {

    Page<Contact> findAll(ContactFiltersDto filters, Pageable pageable);

    List<Contact> findAll();

    void save(Contact contact);

    Optional<Contact> findById(Integer id);

    void delete(Integer id);

    List<Contact> findByFirstNameOrderByFirstNameAsc(String firstName);
    List<Contact> findByFirstNameNotLike(String nombre);
}
