package com.at.internship.schedule.helper;

import com.at.internship.schedule.dto.ContactFilterDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;

@Deprecated
public class ContactSpecificationHelper {

    private final ContactFilterDto filters;

    public ContactSpecificationHelper(ContactFilterDto filters) {
        this.filters = filters;
    }

    public String firstNameLike() {
        return filters.getFirstNameLike() == null ? null : String.format("%%%s%%", filters.getFirstNameLike());
    }

    public String lastNameLike() {
        return filters.getLastNameLike() == null ? null : String.format("%%%s%%", filters.getLastNameLike());
    }

    public String emailAddressLike() {
        return filters.getEmailAddressLike() == null ? null : String.format("%%%s%%", filters.getEmailAddressLike());
    }

    public LocalDate timeGreaterThan(){
        return filters.getTimeGreaterThan() == null ? null :
            LocalDate.ofInstant(filters.getTimeGreaterThan().toInstant(), ZoneId.systemDefault());
    }

    public LocalDate timeLowerThan() {
       return filters.getTimeLowerThan()== null ? null :
            LocalDate.ofInstant(filters.getTimeLowerThan().toInstant(), ZoneId.systemDefault());
    }

}
