package com.example.restservice.controller;

import com.example.restservice.dto.ApiResponse;
import com.example.restservice.entity.Contact;
import com.example.restservice.repository.ContactRepository;
import com.example.restservice.service.ContactService;
import com.example.restservice.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;
import java.util.List;

@RestController

public class ContactController {

    @Autowired
    private ContactService contactService;


    @GetMapping("/contact")
    @ResponseBody
    public ApiResponse fetchContacts() {
        ApiResponse apiResponse = new ApiResponse();
        try {
            apiResponse.setData(contactService.fetchContacts());
            apiResponse.setSuccess(true);
        } catch (Exception e) {
            apiResponse.setSuccess(false);
            apiResponse.setMessage("An error occurred while fetching contacts");
        }
        return apiResponse;

    }


    @GetMapping("/contact/{id}")
    @ResponseBody
    public ApiResponse viewContact(@PathVariable Long id) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            apiResponse.setData(contactService.viewContact(id));
            apiResponse.setSuccess(true);
        } catch (Exception e) {
            apiResponse.setSuccess(false);
            apiResponse.setMessage("An error occurred while fetching contact:" + id);

        }
        return apiResponse;
    }


    @DeleteMapping(value = "/contact/{id}",
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ApiResponse deleteContact(@PathVariable Long id) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            contactService.deleteContact(id);
            apiResponse.setSuccess(true);

        } catch (Exception e) {
            apiResponse.setSuccess(false);
            apiResponse.setMessage("An error occurred while deleting contact:" + id);
        }
        return apiResponse;

    }
}
