package com.at.internship.schedule.repository;

import com.at.internship.schedule.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IContactRepository extends JpaRepository<Contact, Integer> {
}
