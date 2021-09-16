package com.example.restservice.config;

public class SecurityConstants {
    public static final String SECRET = "SECRET_KEY"; //HS256 algorithm is used here
    public static final long EXPIRATION_TIME = 86_400_000; // 24hrs in milliseconds is best practice against brute forcing attacks
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/account/signup";
    public static final String LOGIN_URL = "/account/login";

}
