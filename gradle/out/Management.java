package com.example.restservice.out;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Management")
public class Management {
    @Id
    @SequenceGenerator(name = "management_sequence",
            sequenceName = "management_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE,
            generator = "management_sequence")
    @Column(name = "id",
            updatable = false,
            nullable = false
            //, columnDefinition = "TEXT"
    )
    private Long id;
    @Column(name = "title",
            updatable = false,
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String title;

  }

