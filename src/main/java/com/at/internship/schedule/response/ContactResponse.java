package com.at.internship.schedule.response;

import java.util.List;

import com.at.internship.schedule.domain.Contact;

import lombok.Data;

@Data
public class ContactResponse extends GenericResponse {
    List<Contact> content;
}
