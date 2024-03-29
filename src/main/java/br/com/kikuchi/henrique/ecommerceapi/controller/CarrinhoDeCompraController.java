package br.com.kikuchi.henrique.ecommerceapi.controller;

import br.com.kikuchi.henrique.ecommerceapi.dto.CarrinhoDeCompraResponseDTO;
import br.com.kikuchi.henrique.ecommerceapi.entity.CarrinhoDeCompra;
import br.com.kikuchi.henrique.ecommerceapi.service.CarrinhoDeCompraService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/carrinho-de-compras")
@RequiredArgsConstructor
public class CarrinhoDeCompraController {

    private final CarrinhoDeCompraService carrinhoDeCompraService;

    @GetMapping
    public ResponseEntity<Page<CarrinhoDeCompraResponseDTO>> getAllCarrinhoDeCompra(Pageable pageable){
        return ResponseEntity.ok().body(carrinhoDeCompraService.getAllCarrinhoDeCompra(pageable));
    }

    @GetMapping("{idCarrinho}")
    public ResponseEntity<CarrinhoDeCompra> getCarrinhoById(@PathVariable("idCarrinho") Long idCarrinho){
        return ResponseEntity.ok().body(carrinhoDeCompraService.getCarrinhoDeCompraById(idCarrinho));
    }

    @PostMapping("/create")
    public ResponseEntity<?> saveCarrinhoDeCompra(@RequestBody ProdutosDto produtosDto){
        return ResponseEntity.ok().body(carrinhoDeCompraService.saveCarrinhoDeCompra(produtosDto.produtosList()));
    }

    @PutMapping("realize/{idCarrinho}")
    public ResponseEntity<?> realizarPedido(@PathVariable("idCarrinho") Long idCarrinho){
        carrinhoDeCompraService.realizarCarrinhoDeCompra(idCarrinho);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("cancel/{idCarrinho}")
    public ResponseEntity<?> cancelarPedido(@PathVariable("idCarrinho") Long idCarrinho){
        carrinhoDeCompraService.cancelarCarrinhoDeCompra(idCarrinho);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("delete/{idCarrinho}")
    public ResponseEntity<?> removeCarrinhoDeCompra(@PathVariable("idCarrinho") Long idCarrinho){
        carrinhoDeCompraService.removeCarrinhoDeCompra(idCarrinho);
        return ResponseEntity.noContent().build();
    }
}

record ProdutosDto(List<Long> produtosList){};