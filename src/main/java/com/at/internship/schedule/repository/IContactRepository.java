package com.at.internship.schedule.repository;

import com.at.internship.schedule.domain.Contact;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Repository
public interface IContactRepository extends JpaRepository<Contact, Integer> {
    Page<Contact> findAll(Specification specs, Pageable pageable);
}
