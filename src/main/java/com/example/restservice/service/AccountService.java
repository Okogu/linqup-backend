package com.example.restservice.service;

import com.example.restservice.dto.AccountDto;
import com.example.restservice.entity.Account;
import com.example.restservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(AccountDto accountDto) {
        Account returnValue = new Account();
        returnValue.setFullName(accountDto.getFullName());
        returnValue.setEmail(accountDto.getEmail());
        returnValue.setPassword(accountDto.getPassword());

        return accountRepository.save(returnValue);
    }

    public List<Account> fetchAccount() {
        return accountRepository.findAll();
    }

    public Optional<Account> findById(Long id) { //revert
        return accountRepository.findById(id);
    }

    public Account viewAccount(String fullName) {
        return accountRepository.findByFullName(fullName);
    }

    public Account editAccount(Account account) throws Exception {
        Optional<Account> createdAccountOptional = accountRepository.findById(account.getId());
        //Account createdAccount = accountRepository.findByFullName(account.getFullName());
        if (createdAccountOptional.isEmpty()) {
            //if (createdAccount == null) {
            throw new Exception("Account does not exist");
        } else {
            Account createdAccount = createdAccountOptional.get();
            createdAccount.setFullName(account.getFullName());
            createdAccount.setEmail(account.getEmail());
            createdAccount.setPassword(account.getPassword());
            accountRepository.save(createdAccount);
            // return createdAccount;
            return createdAccountOptional.get();
        }
    }


    public void deleteAccount(String fullName) throws Exception {
        Account account = accountRepository.findByFullName(fullName);
        if (fullName == null) {
            throw new Exception("Account " + account + " does not exist");
        } else {
            accountRepository.deleteById(account.getId());
        }
    }

    //revert
//    public Optional<Account> findById(Long accountId) {
//        return null;
//    }
}
