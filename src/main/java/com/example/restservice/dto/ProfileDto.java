package com.example.restservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import static javax.persistence.GenerationType.SEQUENCE;

//dto is used for operations that does not interact with the database

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfileDto {

    private Long id;

    private String profileName;

    private String name;

    private String photoUrl;

    private String logoUrl;

    private String title;

    private String company;

    private String phone;

    private String email;

    private String address;

    private String instagram;

    private String tiktok;

    private String facebook;

    private String twitter;

    private String linkedin;

    private String website;

    private Boolean defaultProfile = false;

}
