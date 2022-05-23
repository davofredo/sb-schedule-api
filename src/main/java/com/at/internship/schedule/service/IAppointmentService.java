package com.at.internship.schedule.service;

import com.at.internship.schedule.domain.Appointment;
import com.at.internship.schedule.dto.AppointmentFiltersDto;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

public interface IAppointmentService {
    Page<Appointment> findAll(AppointmentFiltersDto filters, Pageable pageable);
}
