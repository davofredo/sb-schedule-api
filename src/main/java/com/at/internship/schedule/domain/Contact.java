package com.at.internship.schedule.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Contact {

    @Id
    private Integer id;
    @NotNull(message = "First name is required")
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;
    @Column(name = "email", nullable = false, length = 100)
    private String emailAddress;
    @Column(name = "birth_day", nullable = false)
    private LocalDate birthDay;
    // Lazy load contactPhones
    @Transient
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id", referencedColumnName = "id", insertable = false, updatable = false)
    private List<ContactPhone> contactPhones;
    // Lazy load appointments
    @Transient
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "icontact_id", referencedColumnName = "id", insertable = false, updatable = false)
    private List<Appointment> appointments;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;
        Contact contact = (Contact) o;
        return id.equals(contact.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String toString() {
        return String.format("%s %s (%s)", firstName, lastName, id);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public List<ContactPhone> getContactPhones() {
        return contactPhones;
    }
//
    public List<Appointment> getAppointments() {
        return appointments;
    }

}
