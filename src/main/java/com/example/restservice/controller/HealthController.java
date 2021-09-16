package com.example.restservice.controller;

import com.example.restservice.dto.ApiResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController

public class HealthController {

    @GetMapping("/")
    @ResponseBody
    public ApiResponse fetchProfiles() {
        ApiResponse apiResponse = new ApiResponse();

        apiResponse.setSuccess(true);
        apiResponse.setMessage("Linqup backend is running");

        return apiResponse;

    }
}
