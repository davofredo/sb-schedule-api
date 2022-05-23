package com.at.internship.schedule.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenericResponse<T> {

    private LocalDateTime timestamp;
    private String code;
    private String message;
    private T content;

}
