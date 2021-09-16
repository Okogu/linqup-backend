package com.example.restservice.dto;

import com.example.restservice.entity.Account;
import lombok.*;

import javax.persistence.ManyToOne;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class ReceiveProfileDto {

    private String senderProfileSpecialId;

    private String location;

}
