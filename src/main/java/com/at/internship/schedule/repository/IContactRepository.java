package com.at.internship.schedule.repository;

import java.util.List;

import com.at.internship.schedule.domain.Contact;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IContactRepository extends JpaRepository<Contact, Integer>, JpaSpecificationExecutor<Contact>, CrudRepository<Contact, Integer> {
    List<Contact> findByFirstNameOrderByFirstNameAsc(String nombre);
    //List<Contact> findByIdAndFirstNameAndLastNameAndFirstNameLikeAndLastNameLikeAndEmailAddressAndBirthDay(Integer id, String firstName, String lastName, String firstNameLike, String lastNameLike, String email, String correo);
    List<Contact> findByFirstNameNotLike(String nombre);
    String findByEmailAddressNotLike(String nombre);
}
