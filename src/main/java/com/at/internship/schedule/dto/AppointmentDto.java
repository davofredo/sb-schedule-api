package com.at.internship.schedule.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class AppointmentDto {
    private Integer id;
    private Integer contactId;
    private LocalDateTime time;
    private String subject;
}
