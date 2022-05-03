package com.at.internship.schedule.config;

import com.at.internship.schedule.repository.IAppointmentRepository;
import com.at.internship.schedule.repository.IContactRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DefaultRepositoryConfig {

    @Value("${com.at.internship.schedule.repository.prefix}")
    private static String DEFAULT_REPO;

    private final ApplicationContext context;


    public DefaultRepositoryConfig(ApplicationContext context) {
        this.context = context;
    }

    public IContactRepository contactRepository() {
        if (DEFAULT_REPO.equals("csv")) {
            return this.context.getBean(CsvRepositoryConfig.class).csvContactRepository();
        }else{
            return (IContactRepository) this.context.getBean(MockRepositoryConfig.BEAN_CONTACT_REPOSITORY);
        }
    }

    public IAppointmentRepository appointmentRepository() {
        if (DEFAULT_REPO.equals("csv")) {
            return this.context.getBean(CsvRepositoryConfig.class).csvAppointmentRepository();
        }else{
            return (IAppointmentRepository) this.context.getBean(MockRepositoryConfig.BEAN_APPOINTMENT_REPOSITORY);
        }
    }

}