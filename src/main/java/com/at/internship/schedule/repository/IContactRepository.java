package com.at.internship.schedule.repository;

import com.at.internship.schedule.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.IOException;
import java.util.List;

public interface IContactRepository {
    List<Contact> findAll();

    Contact save(Contact contact) throws IOException;
}
