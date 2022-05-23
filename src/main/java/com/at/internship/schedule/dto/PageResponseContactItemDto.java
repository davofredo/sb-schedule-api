package com.at.internship.schedule.dto;

import lombok.Data;

@Data
public class PageResponseContactItemDto {
    private String timestamp;
    private String code;
    private ContactDetailsDto content;
    private Integer page;
    private Integer size;
    private Integer totalPages;
    private Integer numberOfElement;
    private boolean first;
    private boolean last;
    private boolean empty;
}
