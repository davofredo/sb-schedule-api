package com.at.internship.schedule.service.impl;

import com.at.internship.schedule.domain.Appointment;

import org.springframework.data.domain.Page;
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
