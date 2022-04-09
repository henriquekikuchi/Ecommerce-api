package br.com.kikuchi.henrique.ecommerceapi.handler;

public record ErrorResponse(
        String message,
        String description
) { }
