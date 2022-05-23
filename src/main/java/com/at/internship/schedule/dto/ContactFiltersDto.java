package com.at.internship.schedule.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ContactFiltersDto {
    private String firstName;
    private String lastName;
    private String emailAddress;
    @JsonFormat(pattern = "MM/dd/yyyy HH:mm:ss")
    private Date birthday;
    private String contactNameLike;
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
    public Date getBirthday() {
        return birthday;
    }
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    public String getContactNameLike() {
        return contactNameLike;
    }
    public void setContactNameLike(String contactNameLike) {
        this.contactNameLike = contactNameLike;
    }
}
