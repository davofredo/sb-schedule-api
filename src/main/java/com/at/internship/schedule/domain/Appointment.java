package com.at.internship.schedule.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Appointment {
    private static final String SEQUENCE_NAME = "APPOINTMENT_SEQUENCE";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_NAME)
    @SequenceGenerator(name = SEQUENCE_NAME, sequenceName = SEQUENCE_NAME)
    private Integer id;
    @Column(name = "contact_id")
    private Integer contactId;
    @Column(name = "apmt_time")
    private LocalDateTime time;
    @Column(name = "apmt_subject")
    private String subject;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Contact contact;

    public Appointment(Appointment source) {
        if(source == null)
            return;
        this.id = source.id;
        this.contactId = source.contactId;
        this.time = source.time;
        this.subject = source.subject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Appointment)) return false;
        Appointment that = (Appointment) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String toString() {
        return String.format("%s. %s -- %s : %s", id, time, contact, subject);
    }

}
