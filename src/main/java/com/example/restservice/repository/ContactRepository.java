package com.example.restservice.repository;

import com.example.restservice.entity.Account;
import com.example.restservice.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ContactRepository extends JpaRepository<Contact, Long> {
//    @Query("SELECT a FROM Contact a WHERE a.name = ?1")
//    Contact findByName(@Param("name")String name);
}
