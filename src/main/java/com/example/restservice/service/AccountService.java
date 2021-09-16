package com.example.restservice.service;

import com.example.restservice.dto.AccountDto;
import com.example.restservice.entity.Account;
import com.example.restservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements UserDetailsService {

    @Autowired
    ProfileService profileService;

    @Autowired
    ContactService contactService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional(rollbackFor = Exception.class)
    //Original
//    public Account createAccount(AccountDto accountDto) {
//        Account returnValue = new Account();
//        returnValue.setFullName(accountDto.getFullName());
//        returnValue.setEmail(accountDto.getEmail());
//        returnValue.setPassword(bCryptPasswordEncoder.encode(accountDto.getPassword()));
//
//        return accountRepository.save(returnValue);
//    }

    public Account createAccount(AccountDto accountDto) throws Exception {
        Optional<Account> Existing = Optional.ofNullable(accountRepository.findByEmail(accountDto.getEmail()));
        if (Existing.isPresent()) {
            throw new Exception("Email has been used to create an account");
        } else {
            Account returnValue = new Account();
            returnValue.setFullName(accountDto.getFullName());
            returnValue.setEmail(accountDto.getEmail());
            returnValue.setPassword(bCryptPasswordEncoder.encode(accountDto.getPassword()));

            return accountRepository.save(returnValue);
        }
    }

    public List<Account> fetchAccount() {
        return accountRepository.findAll();
    }

    public Optional<Account> findById(Long id) { //revert
        return accountRepository.findById(id);
    }

    public Account findByEmail(String email) {
        return accountRepository.findByEmail(email);
    }

    public Account editAccount(AccountDto accountDto, String email) throws Exception {
        Account createdAccountOptional = accountRepository.findByEmail(email);
        if (createdAccountOptional == null) {
            throw new Exception("Account does not exist");
        } else {
            createdAccountOptional.setFullName(accountDto.getFullName());
            createdAccountOptional.setPassword(bCryptPasswordEncoder.encode(accountDto.getPassword()));
            accountRepository.save(createdAccountOptional);
            return createdAccountOptional;


        }
    }


    public void deleteAccount(String email) throws Exception {
        Account account = accountRepository.findByEmail(email);
        if (account == null) {
            throw new Exception("Account with email: " + email + " does not exist");
        } else {
            profileService.deleteAccountById(account.getId());
            contactService.deleteAccountById(account.getId());
            accountRepository.deleteById(account.getId());
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return accountRepository.findByEmail(email);
    }

}
