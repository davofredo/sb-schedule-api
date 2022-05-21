package com.at.internship.schedule.service;


import com.at.internship.schedule.domain.Contact;
import java.util.List;
import java.util.Optional;

public interface IContactService {
    List<Contact> findAll();
    Contact create(Contact contact);
    Contact update(Contact contact);
    Optional<Contact> findById(Integer id);
    Contact deleteContact(Integer id);
}
