package br.com.kikuchi.henrique.ecommerceapi.controller;

import br.com.kikuchi.henrique.ecommerceapi.entity.Fornecedor;
import br.com.kikuchi.henrique.ecommerceapi.service.FornecedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fornecedores")
@RequiredArgsConstructor
public class FornecedorController {

    private final FornecedorService fornecedorService;

    @GetMapping
    public ResponseEntity<List<Fornecedor>> getAllFornecedores(){
        return ResponseEntity.ok().body(fornecedorService.getAllFornecedores());
    }

    @GetMapping(value = "{idFornecedor}")
    public ResponseEntity<Fornecedor> getFornecedorById(@PathVariable("idFornecedor") Long idFornecedor){
        return ResponseEntity.ok().body(fornecedorService.getFornecedorById(idFornecedor));
    }

    @PostMapping(value = "/create")
    public  ResponseEntity<Fornecedor> saveFornecedor(@RequestBody Fornecedor fornecedor){
        return ResponseEntity.ok().body(fornecedorService.saveFornecedor(fornecedor));
    }

    @PutMapping(value = "{idFornecedor}")
    public ResponseEntity<?> updateFornecedor(@PathVariable("idFornecedor") Long idFornecedor,
                                              @RequestBody Fornecedor fornecedor){

        fornecedorService.updateFornecedor(idFornecedor, fornecedor);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "{idFornecedor}")
    public ResponseEntity<?> deleteFornecedor(@PathVariable("idFornecedor") Long idFornecedor) {
        fornecedorService.deleteFornecedorById(idFornecedor);
        return ResponseEntity.noContent().build();
    }
}
