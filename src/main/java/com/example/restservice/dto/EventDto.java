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
public class EventDto {

    private Long id;

    private String type;

    private String title;

    private String description;

    private String location;

    private String date;
}
