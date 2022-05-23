package com.at.internship.schedule.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AppointmentFiltersDto {
    private Integer contactId;
    private String subjectLike;
    @JsonFormat(pattern = "MM/dd/yyyy HH:mm:ss")
    private Date timeGreaterThan;
    private String contactNameLike;
    public Integer getContactId() {
        return contactId;
    }
    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }
    public String getSubjectLike() {
        return subjectLike;
    }
    public void setSubjectLike(String subjectLike) {
        this.subjectLike = subjectLike;
    }
    public Date getTimeGreaterThan() {
        return timeGreaterThan;
    }
    public void setTimeGreaterThan(Date timeGreaterThan) {
        this.timeGreaterThan = timeGreaterThan;
    }
    public String getContactNameLike() {
        return contactNameLike;
    }
    public void setContactNameLike(String contactNameLike) {
        this.contactNameLike = contactNameLike;
    }
}