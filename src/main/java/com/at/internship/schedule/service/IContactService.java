package com.at.internship.schedule.service;

import com.at.internship.schedule.domain.Contact;

import java.util.List;

public interface IContactService {
    List<Contact> findAll();
    Contact findOne(Integer id);
    Contact save(Contact contact);
    Contact delete(Integer id);
}
