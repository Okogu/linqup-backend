package com.example.restservice.out;

import lombok.*;

import javax.persistence.*;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Event")
public class Event {
    @Id
    @SequenceGenerator(name = "event_sequence",
            sequenceName = "event_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE,
            generator = "event_sequence")
    @Column(name = "id",
            updatable = false,
            nullable = false
            //, columnDefinition = "TEXT"
    )
    private Long id;
    @Column(name = "type",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String type;
    @Column(name = "title",
            updatable = false,
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String title;
    @Column(name = "description",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String description;
    @Column(name = "location",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String location;
    @Column(name = "date",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private Date date;


}