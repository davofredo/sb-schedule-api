package com.at.internship.schedule.domain;

import lombok.Data;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Data
@Embeddable
public class AppointmentAssistantPK implements Serializable {

    @ManyToOne
    private Contact contact;

    @ManyToOne
    private Appointment appointment;
}
