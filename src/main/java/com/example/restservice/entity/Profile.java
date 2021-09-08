package com.example.restservice.entity;


import lombok.*;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity(name = "Profile")
@Table(
        name = "profile",
        uniqueConstraints = {
                @UniqueConstraint(
                name = "profile_specialId_unique",
                columnNames = "special_id")
        })
public class Profile {
//    public Set<Account> getCreatedAccount() {
//        return createdAccount;
//    }

    @Id
    @SequenceGenerator(name = "profile_sequence",
            sequenceName = "profile_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = SEQUENCE,
            generator = "profile_sequence")

    //private Set<Account> createdAccount = new HashSet<>();
    @Column(name = "id",
            updatable = false,
            nullable = false
            //, columnDefinition = "TEXT"
    )
    private Long id;

    @Column(name = "special_id",
            nullable = false,
            columnDefinition = "TEXT")
    private String specialId;

    @Column(name = "profile_name",
            nullable = false,
            columnDefinition = "TEXT"
    )

    private String profileName;
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
    @Column(name = "default_profile",
            columnDefinition = "BOOLEAN DEFAULT false" ///revert
    )
    private Boolean defaultProfile;

    @ManyToOne()
    private Account account;
//
//    @OneToOne()
//    private Contact contact;

}