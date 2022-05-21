package com.at.internship.schedule.service.impl;

import com.at.internship.schedule.domain.Contact;
import com.at.internship.schedule.exception.NotFoundRecordException;
import com.at.internship.schedule.exception.NotNullIdException;
import com.at.internship.schedule.exception.NotValidContactException;
import com.at.internship.schedule.repository.IContactRepository;
import com.at.internship.schedule.service.IContactService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImpl implements IContactService {
    private final IContactRepository contactRepository;

    public ContactServiceImpl(IContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public List<Contact> findAll() {
        return contactRepository.findAll();
    }

    @Override
    public Contact create(Contact contact) {
        try{
            Contact savedContact = contactRepository.save(contact);
            return savedContact;
        }catch (NotValidContactException e){
            throw new NotValidContactException("Not Valid");
        }
    }

    @Override
    public Contact update(Contact contact) {
        if (contact.getId() == null) {
            throw new NotNullIdException("ID cannot be null");
        }else{
            Optional optional = contactRepository.findById(contact.getId());
            if(optional.isPresent()) {
                try{
                    Contact contactToUpdate = (Contact) optional.get();
                    contactToUpdate.setFirstName(contact.getFirstName());
                    contactToUpdate.setLastName(contact.getLastName());
                    contactToUpdate.setEmailAddress(contact.getEmailAddress());
                    contactToUpdate.setBirthDay(contact.getBirthDay());

                    Contact updatedContact = contactRepository.save(contactToUpdate);
                    return updatedContact;
                }catch (Exception e){
                    throw new NotValidContactException("Not Valid");
                }
            }else{
                throw new NotFoundRecordException("Not found record");
            }
        }
    }

    @Override
    public Optional<Contact> findById(Integer id) {
        return contactRepository.findById(id);
    }

    @Override
    public Contact deleteContact(Integer id) {
        Optional optionalContact = findById(id);
        if (optionalContact.isPresent()){
            Contact contact = (Contact) optionalContact.get();
            contactRepository.deleteById(contact.getId());
            return contact;
        }else{
            throw new NotFoundRecordException("Requested Contact with ID " + id + " was not Found");
        }
    }
}
