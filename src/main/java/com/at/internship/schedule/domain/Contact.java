package com.at.internship.schedule.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Contact implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    @Column(name = "email")
    private String emailAddress;
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private LocalDate birthDay;

    @OneToMany(mappedBy = "contact", cascade = {CascadeType.ALL})
    private List<Appointment> appointments;

    @OneToMany(mappedBy = "contact", cascade = {CascadeType.ALL})
    private List<ContactPhone> contactPhones;


}
