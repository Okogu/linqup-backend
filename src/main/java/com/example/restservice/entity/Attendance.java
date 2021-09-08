package com.example.restservice.entity;

import lombok.*;

import javax.persistence.*;

import java.sql.Time;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Attendance")
public class Attendance {
    @Id
    @SequenceGenerator(name = "attendance_sequence",
            sequenceName = "attendance_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE,
            generator = "attendance_sequence")
    @Column(name = "id",
            updatable = false,
            nullable = false
            //, columnDefinition = "TEXT"
    )
    private Long id;
    @Column(name = "time",
            updatable = false,
            nullable = false,
            columnDefinition = "TEXT"
    )
    private Time time;
    @Column(name = "location",
            updatable = false,
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String location;

}
