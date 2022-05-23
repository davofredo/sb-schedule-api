package com.at.internship.schedule.utils;

import com.at.internship.schedule.dto.ContactCreateDto;

import java.util.ArrayList;
import java.util.List;

public class ValidateData {
    public List<String> validateNewContact(ContactCreateDto contactCreateDto) {
        ArrayList<String> errorList = new ArrayList<>();
        errorList.add(validateNames(contactCreateDto.getFirstName()));
        errorList.add(validateNames(contactCreateDto.getLastName()));
        errorList.add(validateNameNotEmpty(contactCreateDto.getFirstName()));
        return errorList;
    }

    public String validateNames(String name) {
        if(name.length()<2 || name.length()>255)  {
            return Constants.NAME_TOO_SHORT;
        }
        return "";
    }

    public String validateNameNotEmpty(String name) {
        if(name.isBlank()) {
            return Constants.REQUIRED_NAME;
        }
        return "";
    }

    public String validateEmailIsNotTaken(String email) {
        return null;
    }


}
