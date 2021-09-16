package com.example.restservice.repository;

import com.example.restservice.entity.Account;
import com.example.restservice.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    //  @Query("SELECT a FROM Profile a WHERE a.profileName = :profileName")
    Profile findByProfileName(@Param("profileName") String profileName);

    Optional<Profile> findById(@Param("id") Long id);

    List<Profile> findByAccount(Account account);

    void deleteByAccount(Account account);

    Profile findBySpecialId(@Param("specialId") String specialId);

    Profile findByDefaultProfile(@Param("defaultProfile") Boolean defaultProfile);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM profile  WHERE account_id = :id", nativeQuery = true)
    void deleteAccountById(@Param("id") Long id);


}
