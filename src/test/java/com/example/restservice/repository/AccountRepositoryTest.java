package com.example.restservice.repository;

import com.example.restservice.entity.Account;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class AccountRepositoryTest {

    @Mock
    AccountRepository accountRepository;

    @Autowired
    private AccountRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @BeforeEach
    void setUp() {

    }

    @Test
    void shouldCheckIfAccountExistsByEmail() {

        //given
        String fullName = "Andy Samberg";
        String email = "Andy Samberg";
        String password = "brooklyn99";
        Account account = new Account();
                account.setFullName(fullName);
        account.setEmail(email);
        account.setPassword(password);
        underTest.save(account);

        //when
        Account expected = underTest.findByEmail(email);

        //then
        assertThat(expected).isEqualTo(email);
    }

    @Test
    void shouldCheckIfAccountDoesNotExistByEmail() {
        //given
        String email = "Andy Samberg";


        //when
        Account expected = underTest.findByEmail(email);

        //then
        assertThat(expected).isNull();
    }
}