package com.at.internship.schedule.service.impl;

import java.time.LocalDateTime;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.at.internship.lib.specification.EqualSpec;
import com.at.internship.lib.specification.GreaterSpec;
import com.at.internship.lib.specification.LikeIgnoreCaseSpec;
import com.at.internship.schedule.domain.Appointment;
import com.at.internship.schedule.dto.AppointmentFiltersDto;
import com.at.internship.schedule.repository.IAppointmentRepository;
import com.at.internship.schedule.service.IAppointmentService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImpl implements IAppointmentService {

    private final IAppointmentRepository appointmentRepository;

    public AppointmentServiceImpl(
            IAppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Page<Appointment> findAll(AppointmentFiltersDto filters, Pageable pageable) {
        // Concatenating "%" + filters.getSubjectLike() + "%"
        String subjectLike = filters.getSubjectLike() == null
                ? null
                : String.format("%%%s%%", filters.getSubjectLike());
        // If timeGreaterThan is not null, transform into instant
        LocalDateTime timeGreaterThan = filters.getTimeGreaterThan() == null
                ? null
                : LocalDateTime.ofInstant(filters.getTimeGreaterThan().toInstant(), ZoneId.systemDefault());
                // Concatenating "%" + filters.getContactNameLike() + "%"
        String contactNameLike = filters.getContactNameLike() == null
        ? null
        : String.format("%%%s%%", filters.getContactNameLike());
        // Define specifications
        Specification<Appointment> specs = Specification
                .where(new EqualSpec<Appointment>("contactId", filters.getContactId()))
                .and(new LikeIgnoreCaseSpec<>("subject", subjectLike))
                .and(new GreaterSpec<>("time", timeGreaterThan))
                .and(new ContactNameLikeSpec<>(contactNameLike));
        // Execute query
        return appointmentRepository.findAll(specs, pageable);
    }

    public static class ContactNameLikeSpec<Appointment> implements Specification<Appointment> {
        private String contactName;
        private ContactNameLikeSpec(String contactName) {
            this.contactName = contactName;
        }
        @Override
        public Predicate toPredicate(Root<Appointment> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
            // TODO Auto-generated method stub
            return contactName == null 
            ? criteriaBuilder.conjunction()
            : criteriaBuilder.like(criteriaBuilder.upper(root.join("contact").get("firstName")), contactName.toUpperCase());
        }
    }
}
