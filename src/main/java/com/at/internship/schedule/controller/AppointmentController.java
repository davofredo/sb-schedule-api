package com.at.internship.schedule.controller;

import com.at.internship.schedule.dto.AppointmentDto;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointment")
@SuppressWarnings("unused")
public class AppointmentController {

    private final IAppointmentService appointmentService;
    private final AppointmentConverter appointmentConverter;

    public AppointmentController(
            IAppointmentService appointmentService,
            AppointmentConverter appointmentConverter) {
        this.appointmentService = appointmentService;
        this.appointmentConverter = appointmentConverter;
    }

    @GetMapping("/find")
    public Page<AppointmentDto> findAll(AppointmentFiltersDto filters, Pageable pageable) {
        Page<Appointment> page = appointmentService.findAll(filters, pageable);
        List<AppointmentDto> content = page
                .stream()
                .map(appointmentConverter::toAppointmentDto)
                .collect(Collectors.toList());
        return new PageImpl<>(content, page.getPageable(), page.getTotalElements());
    }
}
