package com.example.restservice.controller;

import com.example.restservice.dto.ApiResponse;
import com.example.restservice.dto.ProfileDto;
import com.example.restservice.dto.ReceiveProfileDto;
import com.example.restservice.entity.Profile;
import com.example.restservice.service.ProfileService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.security.Principal;

@Log4j2
@RestController
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping(value = "/profile",
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ApiResponse createProfile(@RequestBody ProfileDto profileDto, Principal principal) {
        ApiResponse apiResponse = new ApiResponse();

        try {
            apiResponse.setData(profileService.createProfile(profileDto, principal.getName()));
            apiResponse.setSuccess(true);
        } catch (Exception e) {
            apiResponse.setSuccess(false);
            apiResponse.setMessage("An error occurred while creating profiles");
        }
        return apiResponse;

    }


    @GetMapping("/profile/all")
    @ResponseBody
    public ApiResponse fetchProfiles(Principal principal) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            apiResponse.setData(profileService.fetchProfiles(principal.getName()));
            apiResponse.setSuccess(true);
        } catch (Exception e) {
            apiResponse.setSuccess(false);
            apiResponse.setMessage("An error occurred while fetching profiles");
        }
        return apiResponse;

    }


    @GetMapping("/profile/{profileName}")
    @ResponseBody
    public ApiResponse viewProfile(@PathVariable String profileName) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            apiResponse.setData(profileService.viewProfile(profileName));
            apiResponse.setSuccess(true);
        } catch (Exception e) {
            apiResponse.setSuccess(false);
            apiResponse.setMessage("An error occurred while fetching profile:" + profileName);

        }
        return apiResponse;
    }


    @DeleteMapping(value = "/profile/del/{profileName}",
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ApiResponse deleteProfile(@PathVariable String profileName) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            profileService.deleteProfile(profileName);
            apiResponse.setSuccess(true);

        } catch (Exception e) {
            apiResponse.setSuccess(false);
            apiResponse.setMessage("An error occurred while deleting profile:" + profileName);
        }
        return apiResponse;

    }


    @PatchMapping(value = "/profile/mod/{profileName}",
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ApiResponse updateProfile(@RequestBody Profile profile) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            apiResponse.setData(profileService.updateProfile(profile));
            apiResponse.setSuccess(true);

        } catch (Exception e) {
            apiResponse.setSuccess(false);
            apiResponse.setMessage("An error occurred while updating profile");

//            apiResponse.setMessage("An error occurred while updating profile:" + profile.getId());
            log.info(e);
        }
        return apiResponse;
    }

    @PostMapping(value = "/profile/receive",
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ApiResponse receiveProfile(@RequestBody ReceiveProfileDto receiveProfileDto, Principal principal) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            profileService.receiveProfile(receiveProfileDto, principal.getName());
            apiResponse.setSuccess(true);
        } catch (Exception e) {
            apiResponse.setSuccess(false);
            apiResponse.setMessage("An exception occurred. Could not receive profile");
            log.error(e);
        }
        return apiResponse;
    }
}

