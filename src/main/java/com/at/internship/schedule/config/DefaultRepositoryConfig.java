package com.at.internship.schedule.config;

import com.at.internship.schedule.repository.IAppointmentRepository;
import com.at.internship.schedule.repository.IContactRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

// @Configuration
public class DefaultRepositoryConfig {

    private final ApplicationContext context;

    public DefaultRepositoryConfig(ApplicationContext context) {
        this.context = context;
        // this.context.getBean();
    }

    public IContactRepository contactRepository() {
        //
        return null;
    }

    public IAppointmentRepository appointmentRepository() {
        //
        return null;
    }

}
