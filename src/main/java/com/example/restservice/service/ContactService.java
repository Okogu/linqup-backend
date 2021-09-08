package com.example.restservice.service;

import com.example.restservice.entity.Contact;
import com.example.restservice.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;
//    @Autowired
//    private AccountService accountService;

    public Contact saveContact(Contact contact) {
        return contactRepository.save(contact);
    }

    public List<Contact> fetchContacts() {
        return contactRepository.findAll();
    }

    public Optional<Contact> viewContact(Long id) {
        return contactRepository.findById(id);
    }

    public void deleteContact(Long id) throws Exception {
        Optional<Contact> contact = contactRepository.findById(id);
        if (id == null) {
            throw new Exception("Contact " + contact + " does not exist");
        } else {
            contactRepository.deleteById(contact.get().getId());
        }
    }
}
