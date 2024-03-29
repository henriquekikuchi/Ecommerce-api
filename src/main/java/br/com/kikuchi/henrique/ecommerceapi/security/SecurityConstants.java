package br.com.kikuchi.henrique.ecommerceapi.security;

public class SecurityConstants {

    public static final String SECRET = "secret123";
    public static final String AUTHORITIES_KEY = "role";
    public static final long EXPIRATION_TIME = 900_000; // 15 mins
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/auth";

}
