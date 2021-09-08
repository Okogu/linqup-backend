package com.example.restservice.dto;

import com.example.restservice.entity.Account;
import lombok.*;

import javax.persistence.ManyToOne;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class ShareProfileDto {

    private Long senderProfileId;

    private Long recipientAccountId;

    private String location;

}
