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

@Log4j2
@RestController


public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping(value = "/account",
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Account createAccount(@RequestBody AccountDto accountDto) {
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


    @GetMapping("/account/{fullName}")
    @ResponseBody
    public ApiResponse viewAccount(@PathVariable String fullName) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            apiResponse.setData(accountService.viewAccount(fullName));
            apiResponse.setSuccess(true);
        } catch (Exception e) {
            apiResponse.setSuccess(false);
            apiResponse.setMessage("An error occurred while fetching account:" + fullName);

        }
        return apiResponse;
    }


    @DeleteMapping(value = "/account/{fullName}",
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ApiResponse deleteAccount(@PathVariable String fullName) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            accountService.deleteAccount(fullName);
            apiResponse.setSuccess(true);

        } catch (Exception e) {
            apiResponse.setSuccess(false);
            apiResponse.setMessage("An error occurred while deleting account:" + fullName);
        }
        return apiResponse;

    }


    @PatchMapping(value = "/account/{fullName}",
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public ApiResponse editAccount(@RequestBody Account account) {
        ApiResponse apiResponse = new ApiResponse();
        try {
            apiResponse.setData(accountService.editAccount(account));
            apiResponse.setSuccess(true);

        } catch (Exception e) {
            apiResponse.setSuccess(false);
            apiResponse.setMessage("An error occurred while updating account");

//            apiResponse.setMessage("An error occurred while updating account:" + account.getId());
            log.info(e);
        }
        return apiResponse;
    }

}
