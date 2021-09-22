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
@Table(
        name = "contact",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "contact_profile_specialId_unique",
                        columnNames = "contact_profile_special_id")
        })

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
    )

    private Long id;
    @Column(name = "category",
            columnDefinition = "TEXT"
    )
    private String category;

    @Column(name = "contact_profile_special_id",
            columnDefinition = "TEXT")
    private String specialId;

    @Column(name = "location",
            updatable = false,
            columnDefinition = "TEXT"
    )
    private String location;
    @Column(name = "date_received",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private java.util.Date receivedDate;

    @ManyToOne
    private Account account;

    @Column(name = "name",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;
    @Column(name = "display_photo",
            columnDefinition = "TEXT"
    )
    private String photoUrl;
    @Column(name = "logo",
            columnDefinition = "TEXT"
    )
    private String logoUrl;
    @Column(name = "title",
            columnDefinition = "TEXT"
    )
    private String title;
    @Column(name = "company",
            columnDefinition = "TEXT"
    )
    private String company;
    @Column(name = "phone_number",
            columnDefinition = "TEXT"
    )
    private String phone;
    @Column(name = "email",
            columnDefinition = "TEXT"
    )
    private String email;
    @Column(name = "address",
            columnDefinition = "TEXT"
    )
    private String address;
    @Column(name = "instagran",
            columnDefinition = "TEXT"
    )
    private String instagram;
    @Column(name = "tiktok",
            columnDefinition = "TEXT"
    )
    private String tiktok;
    @Column(name = "facebook",
            columnDefinition = "TEXT"
    )
    private String facebook;
    @Column(name = "twitter",
            columnDefinition = "TEXT"
    )
    private String twitter;
    @Column(name = "linkedin",
            columnDefinition = "TEXT"
    )
    private String linkedin;
    @Column(name = "website",
            columnDefinition = "TEXT"
    )
    private String website;

    @PrePersist
    protected void onCreate() {
        receivedDate = new Date();
    }

}