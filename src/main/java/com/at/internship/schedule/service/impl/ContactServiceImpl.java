package com.at.internship.schedule.service.impl;

import com.at.internship.schedule.domain.Contact;
import com.at.internship.schedule.dto.ContactFilterDto;
import com.at.internship.schedule.exception.EmailTakenException;
import com.at.internship.schedule.exception.NotFoundRecordException;
import com.at.internship.schedule.exception.NotNullIdException;
import com.at.internship.schedule.exception.NotValidContactException;
import com.at.internship.schedule.lib.specification.EqualSpec;
import com.at.internship.schedule.lib.specification.GreaterSpec;
import com.at.internship.schedule.lib.specification.LikeIgnoreCaseSpec;
import com.at.internship.schedule.lib.specification.LowerSpec;
import com.at.internship.schedule.repository.IContactRepository;
import com.at.internship.schedule.service.IContactService;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Optional;

@Service
public class ContactServiceImpl implements IContactService {
    private final IContactRepository contactRepository;

    public ContactServiceImpl(IContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public Page<Contact> findAll(ContactFilterDto filters, Pageable pageable) {
        String firstNameLike =
            filters.getFirstNameLike() == null ? null : String.format("%%%s%%", filters.getFirstNameLike());

        String lastNameLike =
            filters.getLastNameLike() == null ? null : String.format("%%%s%%", filters.getLastNameLike());

        String emailAddressLike =
            filters.getEmailAddressLike() == null ? null : String.format("%%%s%%", filters.getEmailAddressLike());

        LocalDate timeGreaterThan = filters.getTimeGreaterThan() == null ? null :
            LocalDate.ofInstant(filters.getTimeGreaterThan().toInstant(), ZoneId.systemDefault());

        LocalDate timeLowerThan = filters.getTimeLowerThan()== null ? null :
            LocalDate.ofInstant(filters.getTimeLowerThan().toInstant(), ZoneId.systemDefault());

        LocalDate timeEqualLike = filters.getTimeEqualLike()== null ? null :
            LocalDate.ofInstant(filters.getTimeEqualLike().toInstant(), ZoneId.systemDefault());

        String fullNameLike =
            filters.getFullNameLike() == null ? null : String.format("%%%s%%", filters.getFullNameLike());

        //Define Specifications
        Specification<Contact> specs = Specification
            .where(new EqualSpec<Contact>("id", filters.getId()))
            .and(new LikeIgnoreCaseSpec<>("firstName", firstNameLike))
            .and(new LikeIgnoreCaseSpec<>("lastName", lastNameLike))
            .and(new LikeIgnoreCaseSpec<>("emailAddress", emailAddressLike))
            .and(new GreaterSpec<>("birthDay", timeGreaterThan))
            .and(new LowerSpec<>("birthDay",timeLowerThan))
            .and(new EqualSpec<>("birthDay",timeEqualLike))
            .and(new LikeIgnoreCaseSpec<>("fullName", fullNameLike));

        return contactRepository.findAll(specs, pageable);
    }

    @Override
    public Contact create(Contact contact) {
        Optional<String> contactOptional = contactRepository
            .findContactByEmail(contact.getEmailAddress());

        if (contactOptional.isPresent()) {
            throw new EmailTakenException(
                String.format("The email %s is already taken by another contact",
                    contact.getEmailAddress()));
        }

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
            throw new NotFoundRecordException(
                String.format("Requested Contact with ID: %s  was not Found", id)
            );
        }
    }

}
