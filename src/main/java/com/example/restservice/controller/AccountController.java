package com.example.restservice.controller;
//main entry point for the user to interact with API

import com.example.restservice.dto.AccountDto;
import com.example.restservice.dto.ApiResponse;
import com.example.restservice.entity.Account;
import com.example.restservice.service.AccountService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.MediaType;

import java.security.Principal;

import static com.example.restservice.config.SecurityConstants.SIGN_UP_URL;

@Log4j2
@RestController


public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping(value = SIGN_UP_URL,
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    //Original
//    public Account createAccount(@RequestBody AccountDto accountDto) {
//        return accountService.createAccount(accountDto);
//    }
    public Account createAccount(@RequestBody AccountDto accountDto) throws Exception {
        return accountService.createAccount(accountDto);
    }

    @GetMapping("/account")
    @ResponseBody
    public ApiResponse fetchAccount() {
        ApiResponse apiResponse = new ApiResponse();

        try {
            apiResponse.setData(accountService.fetchAccount());
            apiResponse.setSuccess(true);
        } catch (Exception e) {
            apiResponse.setSuccess(false);
            apiResponse.setMessage("An error occurred while fetching account");
        }
        return apiResponse;
    }


    @GetMapping("/account/{email}")
    @ResponseBody
    public ApiResponse findByEmail(@PathVariable String email) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            apiResponse.setData(accountService.findByEmail(email));
            apiResponse.setSuccess(true);
        } catch (Exception e) {
            apiResponse.setSuccess(false);
            apiResponse.setMessage("An error occurred while fetching account:" + email);

        }
        return apiResponse;
    }


    @DeleteMapping(value = "/account/del",
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ApiResponse deleteAccount(Principal principal) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            accountService.deleteAccount(principal.getName());
            apiResponse.setSuccess(true);

        } catch (Exception e) {
            apiResponse.setSuccess(false);
            apiResponse.setMessage("An error occurred while deleting account:" + principal.getName());
        }
        return apiResponse;

    }


    @PatchMapping(value = "/account/mod",
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ApiResponse editAccount(@RequestBody AccountDto accountDto, Principal principal) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            apiResponse.setData(accountService.editAccount(accountDto, principal.getName()));
            apiResponse.setSuccess(true);

        } catch (Exception e) {
            apiResponse.setSuccess(false);
            apiResponse.setMessage("An error occurred while updating account");


            log.info(e);
        }
        return apiResponse;
    }

}
