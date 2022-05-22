package com.at.internship.schedule.service;

import com.at.internship.schedule.domain.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IContactService {
    Page<Contact> findAll(Pageable pageable);
    Contact findOne(Integer id);
    Contact save(Contact contact);
    Contact delete(Integer id);
}
