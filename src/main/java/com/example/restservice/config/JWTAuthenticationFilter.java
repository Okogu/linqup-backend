package com.example.restservice.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.restservice.dto.AccountDto;
import com.example.restservice.entity.Account;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.example.restservice.config.SecurityConstants.EXPIRATION_TIME;
import static com.example.restservice.config.SecurityConstants.SECRET;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    //UsernamePasswordAuthenticationFilter is the default class for password authentication in spring security
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;

        setFilterProcessesUrl("/account/login");// sets default login URL, spring security automatically creates /login endpoint if none specified
    }

    @Override
    //runs when the user tries to login to Linqup, it reads the credentials the user puts in and creates a user POJO from it and authenticates the credentials
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            AccountDto creds = new ObjectMapper()
                    .readValue(req.getInputStream(), AccountDto.class);

            return authenticationManager.authenticate( //this object contains the authorities i pass while attempting to authenticate
                                                       new UsernamePasswordAuthenticationToken(
                                                               creds.getEmail(),
                                                               creds.getPassword(),
                                                               new ArrayList<>())// represents authority roles, no roles yet so empty
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    //runs when authentication is successful, parameters are passed by spring security BTS
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException {
        String token = JWT.create()
                          .withSubject(((Account) auth.getPrincipal()).getEmail())
                          .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) ///
                          .sign(Algorithm.HMAC512(SECRET.getBytes()));

        String body = "{\"email\":\"" + ((Account) auth.getPrincipal()).getEmail() + "\",\"token\":\"" + token + "\"}";

        res.getWriter().write(body);
        res.getWriter().flush();
    }
}
