package com.example.restservice.out;

import com.example.restservice.out.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface EventRepository extends JpaRepository<Event, Long> {
    // @Query("SELECT a FROM Event a WHERE a.title = ?1")
    Event findByTitle(@Param("title") String title);

//    Event findByDate(Instant date);


}
