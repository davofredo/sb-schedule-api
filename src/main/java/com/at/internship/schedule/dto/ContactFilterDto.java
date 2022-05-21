package com.at.internship.schedule.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;

@Data
public class ContactFilterDto {
    private Integer id;
    private String firstNameLike;
    private String lastNameLike;
    private String emailAddressLike;
    @JsonFormat(pattern = "MM/dd/yyyy")
    private Date timeGreaterThan;
}
