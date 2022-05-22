package com.at.internship.schedule.repository;

import com.at.internship.schedule.domain.Contact;
import com.at.internship.schedule.domain.ContactPhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IContactRepository extends
    JpaRepository<Contact, Integer>,
    JpaSpecificationExecutor<Contact> {

    //@Query(value = "SELECT * FROM contact AS c JOIN contact_phone AS cp ON c.id = cp.contact_id WHERE cp.contact_id = ?;")
    //List<ContactPhone> findByContactId(Integer id);
}
