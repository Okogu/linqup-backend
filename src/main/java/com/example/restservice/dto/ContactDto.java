package com.example.restservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactDto {


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

    private Long accountId;

    private Long senderProfileId;

    private String location;

    private Date receivedDate;
}
