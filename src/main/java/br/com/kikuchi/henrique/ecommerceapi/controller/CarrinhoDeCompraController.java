package br.com.kikuchi.henrique.ecommerceapi.controller;

import br.com.kikuchi.henrique.ecommerceapi.entity.CarrinhoDeCompra;
import br.com.kikuchi.henrique.ecommerceapi.repository.CarrinhoDeCompraRepository;
import br.com.kikuchi.henrique.ecommerceapi.service.CarrinhoDeCompraService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/carrinho-de-compras")
@RequiredArgsConstructor
public class CarrinhoDeCompraController {

    private final CarrinhoDeCompraService carrinhoDeCompraService;

    @GetMapping
    public ResponseEntity<?> saveCarrinhoDeCompra(
            @CurrentSecurityContext(expression="authentication?.name") String username){

        return ResponseEntity.ok().body(carrinhoDeCompraService.saveCarrinhoDeCompra(username));
    }
}
