package com.at.internship.schedule.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class AppointmentAssistant implements Serializable {

    @EmbeddedId
    private AppointmentAssistantPK appointmentAssistantPK;
    @Column(nullable = false, length = 30)
    private String type;
}
