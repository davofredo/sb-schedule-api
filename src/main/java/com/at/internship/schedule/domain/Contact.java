package com.at.internship.schedule.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Data
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "First name cannot be null")
    @Size(min = 2, max = 50, message = "First name should be at least {min} characters length and {max} maximum")
    @Column(name = "first_name")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    @Size(min = 2, max = 50, message = "Last name should be at least {min} characters length and {max} maximum")
    @Column(name = "last_name")
    private String lastName;

    @Email(message = "Please provide a valid email address")
    @NotNull(message = "Email cannot be null")
    @Size(max = 100, message = "Last name should be {max} length maximum")
    @Column(name = "email", length = 100)
    private String emailAddress;

    @NotNull(message = "Birth day cannot be null")
    @Column(name = "birth_day")
    @JsonFormat(pattern = "MM/dd/yyy")
    private LocalDate birthDay;

    // Lazy load contactPhones
    @OneToMany(mappedBy = "contact", fetch = FetchType.LAZY)
    private List<ContactPhone> contactPhones;

    // Lazy load appointments
    @Transient
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

}
