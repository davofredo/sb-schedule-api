package com.at.internship.schedule.service.impl;

import com.at.internship.schedule.domain.Contact;
import com.at.internship.schedule.dto.ContactFiltersDto;
import com.at.internship.schedule.repository.AppointmentRepository;
import com.at.internship.schedule.repository.ContactPhoneRepository;
import com.at.internship.schedule.repository.ContactRepository;
import com.at.internship.schedule.service.IContactService;
import com.at.internship.schedule.util.helper.ContactSpecificationsHelper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Objects;

@Service
public class ContactService implements IContactService {

    private final ContactRepository contactRepository;
    private final ContactPhoneRepository contactPhoneRepository;
    private final AppointmentRepository appointmentRepository;

    public ContactService(ContactRepository contactRepository, AppointmentRepository appointmentRepository, ContactPhoneRepository contactPhoneRepository) {
        this.contactRepository = contactRepository;
        this.appointmentRepository = appointmentRepository;
        this.contactPhoneRepository = contactPhoneRepository;
    }

    @Override
    public Page<Contact> findAll(ContactFiltersDto filters, Pageable pageable) {
        return contactRepository.findAll(ContactSpecificationsHelper.getContactSpecs(filters),pageable);
    }

    @Override
    public Contact findOne(Integer id) {
        return contactRepository.findById(id)
                .orElseThrow(() -> {throw new EntityNotFoundException(String.format("Requested Contact with ID %s was not found", id));});
    }

    @Override
    public Contact save(Contact contact) {
        if(contact.getId() != null) this.findOne(contact.getId());

        Contact contactFound = contactRepository.findByEmailAddress(contact.getEmailAddress());
        if(contactFound !=null && !Objects.equals(contact.getId(), contactFound.getId()))
            throw new DataIntegrityViolationException(String.format("The email %s is already taken by another contact", contact.getEmailAddress()));

        Contact contactSaved = contactRepository.save(contact);
        contactSaved.setContactPhones(contactPhoneRepository.findAllByContact(contactSaved));
        return contactSaved;
    }

    @Override
    public Contact delete(Integer id, boolean force) {
        Contact contact = this.findOne(id);
        if(!contact.getAppointments().isEmpty()){
            if(force) appointmentRepository.deleteAll(contact.getAppointments());
            else throw new DataIntegrityViolationException(String.format("Contact #%s cannot be deleted because there are appointments assigned to them", id));
        }
        if(!contact.getContactPhones().isEmpty()){
            if(force) contactPhoneRepository.deleteAll(contact.getContactPhones());
            else throw new DataIntegrityViolationException(String.format("Contact #%s cannot be deleted because there are contact phones assigned to them", id));
        }
        contactRepository.delete(contact);
        return contact;
    }
}
