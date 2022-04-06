package br.com.kikuchi.henrique.ecommerceapi.controller;

import br.com.kikuchi.henrique.ecommerceapi.entity.Categoria;
import br.com.kikuchi.henrique.ecommerceapi.service.CategoriaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;


    @GetMapping
    public ResponseEntity<List<Categoria>> getAllCategorias(){
        return ResponseEntity.ok().body(categoriaService.getAllCategorias());
    }

    @GetMapping(value = "{idCategoria}")
    public ResponseEntity<Categoria> getCategoriaById(@PathVariable("idCategoria") Long idCategoria){
        return ResponseEntity.ok().body(categoriaService.getCategoriaById(idCategoria));
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Categoria> saveCategoria(@RequestBody Categoria categoria){
        return ResponseEntity.ok().body(categoriaService.saveCategoria(categoria));
    }

    @PutMapping(value = "/{idCategoria}")
    public ResponseEntity<?> updateCategoria(@PathVariable("idCategoria") Long idCategoria,
                                             @RequestBody Categoria categoria){

        categoriaService.updateCategoria(idCategoria, categoria);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "{idCategoria}")
    public ResponseEntity<?> deleteCategoria(@PathVariable("idCategoria") Long idCategoria){
        categoriaService.deleteCategoriaById(idCategoria);
        return ResponseEntity.noContent().build();
    }
}

