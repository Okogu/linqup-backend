package com.example.restservice.repository;

import com.example.restservice.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    //  @Query("SELECT a FROM Profile a WHERE a.profileName = :profileName")
    Profile findByProfileName(@Param("profileName") String profileName);

    Optional<Profile> findById(@Param("id") Long id);

    Profile findByDefaultProfile(@Param("defaultProfile") Boolean defaultProfile);

    //   @Query("DELETE FROM Profile  WHERE profileName = ?1")
//    Profile delete(@Param("profileName")String profileName);

//    @Query(value = "SELECT * FROM Profile  WHERE profileName = ?1")
//    Profile findAllBy(@Param("profileName")String profileName);

}
