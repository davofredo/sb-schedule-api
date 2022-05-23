package com.at.internship.schedule.response;

import com.at.internship.schedule.domain.Contact;

public class ContactResponse extends GenericResponse {

    Contact content;
    
    public Contact getContent() {
        return content;
    }

    public void setContent(Contact contact) {
        this.content = contact;
    }
}
