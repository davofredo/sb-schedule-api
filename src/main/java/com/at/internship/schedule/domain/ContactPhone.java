package com.at.internship.schedule.domain;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
public class ContactPhone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "contact_id", length = 11)
    private Integer contactId;
    @Column(name = "phone_number", length = 50)
    private String phoneNumber;
    @Column(name = "label", length = 100)
    private String label;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contact_id", referencedColumnName = "id", insertable = false, updatable = false)
    private Contact contact;
}
