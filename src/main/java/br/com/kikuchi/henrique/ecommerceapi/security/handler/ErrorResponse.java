package br.com.kikuchi.henrique.ecommerceapi.security.handler;

public record ErrorResponse(
        int status,
        String message
) { }
