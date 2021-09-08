package com.example.restservice.entity;

import lombok.*;

import javax.persistence.*;

import java.util.Date;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Contact")
public class Contact {
    @Id
    @SequenceGenerator(name = "contact_sequence",
            sequenceName = "contact_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE,
            generator = "contact_sequence")
    @Column(name = "id",
            updatable = false,
            nullable = false
            //, columnDefinition = "TEXT"
    )
    private Long id;
    //    private final String contactName;
    @Column(name = "category",
//            updatable = false,
//
            columnDefinition = "TEXT"
    )
    private String category;
//    @Column(name = "name",
//            updatable = false,
//            nullable = false,
//            columnDefinition = "TEXT"
//    )
//    private String name;
    @Column(name = "location",
            updatable = false,
//            nullable = false,
            columnDefinition = "TEXT"
    )
    private String location;
    @Column(name = "date_received",
//            updatable = false,
            nullable = false,
            columnDefinition = "TEXT"
    )
    private java.util.Date receivedDate;

    @PrePersist
    protected void onCreate() {
        receivedDate = new Date();
    }

    @OneToOne
    private Profile profile;

    @ManyToOne
    private Account account;
}