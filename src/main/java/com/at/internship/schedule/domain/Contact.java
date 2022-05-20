package com.at.internship.schedule.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.persistence.*;

@Data
@Entity
public class Contact {
    private static final String SEQUENCE_NAME = "APPOINTMENT_SEQUENCE";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
    private Integer id;
    @Column(name = "first_name", length = 50)
    private String firstName;
    @Column(name = "last_name", length = 50)
    private String lastName;
    @Column(name = "email", length = 100)
    private String emailAddress;
    @Column(name = "birth_day")
    private LocalDate birthDay;
    // Lazy load contactPhones
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "phone_id", referencedColumnName = "id", insertable = false, updatable = false)
    private List<ContactPhone> contactPhones;
    // Lazy load appointments
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id", referencedColumnName = "id", insertable = false, updatable = false)
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
