package com.at.internship.schedule.repository;

import com.at.internship.schedule.domain.Contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer>, JpaSpecificationExecutor<Contact> {

    boolean existsByEmailAddress(String email);
}