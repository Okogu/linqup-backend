package com.example.restservice.repository;

import com.example.restservice.entity.Event;
import com.example.restservice.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;

public interface EventRepository extends JpaRepository<Event, Long> {
    // @Query("SELECT a FROM Event a WHERE a.title = ?1")
    Event findByTitle(@Param("title") String title);

//    Event findByDate(Instant date);


}
