package com.example.restservice.repository;

import com.example.restservice.entity.Account;
import com.example.restservice.entity.Contact;
import com.example.restservice.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    List<Contact> findByAccount(Account account);

    void deleteByAccount(Account account);


    Contact findBySpecialId(@Param("specialId") String specialId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM contact  WHERE account_id = :id", nativeQuery = true)
    void deleteAccountById(@Param("id") Long id);

}
