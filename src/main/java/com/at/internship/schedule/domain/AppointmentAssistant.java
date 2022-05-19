package com.at.internship.schedule.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class AppointmentAssistant implements Serializable {

    @EmbeddedId
    private AppointmentAssistantPK appointmentAssistantPK;

    private String type;
}
