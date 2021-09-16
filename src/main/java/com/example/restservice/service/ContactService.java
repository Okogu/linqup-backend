package com.example.restservice.service;

import com.example.restservice.entity.Account;
import com.example.restservice.entity.Contact;
import com.example.restservice.repository.AccountRepository;
import com.example.restservice.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;
    @Autowired
    private AccountService accountService;

    public Contact saveContact(Contact contact) {

        return contactRepository.save(contact);
    }

    public Contact findBySpecialId(String specialId) {
        return contactRepository.findBySpecialId(specialId);
    }

    public List<Contact> fetchContacts(String email) throws Exception {
        Account existingAccountOptional = accountService.findByEmail(email);
        if (existingAccountOptional == null) {
            throw new Exception("Account with email: " + email + " does not exist");
        } else {
            return contactRepository.findByAccount(existingAccountOptional);
        }
    }


    public Optional<Contact> viewContact(Long id) {
        return contactRepository.findById(id);
    }

    public void deleteContact(Long id, String email) throws Exception {
        Account existingAccount = accountService.findByEmail(email);
        if (existingAccount == null) {
            throw new Exception("account does not exist");
        }
        Optional<Contact> contactOptional = contactRepository.findById(id);

        if (contactOptional.isEmpty()) {
            throw new Exception("Contact " + contactOptional + " does not exist");
        }
        if (existingAccount.getId() != contactOptional.get().getAccount().getId()) {
            throw new Exception("cannot delete contact that is not your own");
        }

        contactRepository.deleteById(contactOptional.get().getId());

    }

    public void deleteByAccount(Account account) {
        contactRepository.deleteByAccount(account);
    }

    public void deleteAccountById(Long id) {
        contactRepository.deleteAccountById(id);
    }
}
