package com.example.restservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class ApiResponse implements Serializable {
    boolean success;
    Object data;
    String message;
}
