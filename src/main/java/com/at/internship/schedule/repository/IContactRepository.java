package com.at.internship.schedule.repository;

import com.at.internship.schedule.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IContactRepository extends JpaRepository<Contact, Integer> {

}
