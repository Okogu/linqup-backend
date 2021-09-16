package com.example.restservice.service;

import com.example.restservice.dto.AccountDto;
import com.example.restservice.entity.Account;
import com.example.restservice.repository.AccountRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;
    //    private AutoCloseable autoCloseable;
    private AccountService underTest;

//    private AccountDto accountDto;

    @BeforeEach
    void setUp() {
//        AutoCloseable autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new AccountService();
    }

//    @AfterEach
//    void tearDown() throws Exception {
//        autoCloseable.close();
//    }

    @Test
    @Disabled
    void createAccount() throws Exception {
        //given
        String fullName = "Andy Samberg";
        String email = "Andy Samberg";
        String password = "brooklyn99";
        Account account = new Account();
        account.setFullName(fullName);
        account.setEmail(email);
        account.setPassword(password);
//        underTest.save(account);
//
//        //when
//        underTest.createAccount(accountDto);
//        //then
//        ArgumentCaptor<AccountDto> accountDtoArgumentCaptor =
//                ArgumentCaptor.forClass(AccountDto.class);
//        verify(accountRepository).save(accountDtoArgumentCaptor.capture());

    }

    @Test
    void fetchAccount() {
        //when
        underTest.fetchAccount();
        //then
        verify(accountRepository).findAll();
    }

    @Test
    @Disabled
    void findById() {
    }

    @Test
    @Disabled
    void viewAccount() {
    }

    @Test
    @Disabled
    void editAccount() {
    }

    @Test
    @Disabled
    void deleteAccount() {
    }

    @Test
    @Disabled
    void loadUserByUsername() {
    }
}