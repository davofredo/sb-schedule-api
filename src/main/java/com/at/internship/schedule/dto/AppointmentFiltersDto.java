package com.at.internship.schedule.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class AppointmentFiltersDto {
    private Integer contactId;
    private String subjectLike;
    @JsonFormat(pattern = "MM/dd/yyyy HH:mm:ss")
    private Date timeGreaterThan;
    private String contactNameLike;
}
