package com.at.internship.schedule.dto;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
public class ContactDto implements Serializable {
    private Integer id;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String birthDay;
    private List<ContactPhoneDto> phoneNumbers;
}
