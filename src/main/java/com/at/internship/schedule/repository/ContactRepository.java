package com.at.internship.schedule.repository;

import com.at.internship.schedule.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Integer> {/*
    @Query("Select s FROM Student s WHERE s.email = ?1")
    Optional<Contact> findContactByEmail(String email);*/
}
