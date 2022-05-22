package com.at.internship.schedule.repository;

import com.at.internship.schedule.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface IContactRepository extends
    JpaRepository<Contact, Integer>,
    JpaSpecificationExecutor<Contact> {

    @Query(value = "SELECT email FROM contact WHERE email = ?1", nativeQuery = true)
    Optional<String> findContactByEmail(String email);
}
