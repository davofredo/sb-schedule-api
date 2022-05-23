package com.at.internship.schedule.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class ContactDto {
    private Integer id;
    @NotNull(message = "First name is required")
    private String firstName;
    @NotNull(message = "Last name is required")
    private String lastName;
    @NotNull
    @Email(message = "Please provide a valid email address")
    private String emailAddress;
    @NotNull(message = "invalid birthday")
    private String birthDay;
    //@NotNull
    //@Min(value = 10, message =  "Phone numbers contain 10 characters")
    //@Max(value = 10)
    //@Digits(integer = 10, fraction = 0, message = "Pone numbers only contains numbers")
    //private List<ContactPhone> contactPhones;
    //private List<Appointment> appointments;
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
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
    public String getBirthDay() {
        return birthDay;
    }
    public void setBirthDay(String localDate) {
        this.birthDay = localDate;
    }
    //public List<ContactPhone> getContactPhones() {
    //    return contactPhones;
    //}
    //public void setContactPhones(List<ContactPhone> contactPhones) {
    //    this.contactPhones = contactPhones;
    //}
    //public List<Appointment> getAppointments() {
    //    return appointments;
    //}
    //public void setAppointments(List<Appointment> appointments) {
    //    this.appointments = appointments;
    //}
}
