package com.example.restservice.repository;

import com.example.restservice.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    //  @Query("FROM Account  WHERE email = ?1")
    Account findByEmail(@Param("email") String email);


    //    @Query("SELECT a FROM Account a WHERE a.fullName = ?1")
    Account findByFullName(@Param("fullName") String fullName);

//    Account findById( @Param("id")Long id);


}
