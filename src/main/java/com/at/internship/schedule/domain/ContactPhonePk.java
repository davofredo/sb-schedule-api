package com.at.internship.schedule.domain;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable /**Tenemos que implementar serializable, indica que pueden ser guardados*/
public class ContactPhonePk implements Serializable {
    private Integer contactId;
    private String phoneNumber;
}
