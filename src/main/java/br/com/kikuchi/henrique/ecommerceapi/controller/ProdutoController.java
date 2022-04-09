package br.com.kikuchi.henrique.ecommerceapi.controller;

import br.com.kikuchi.henrique.ecommerceapi.dto.ProdutoCreateDto;
import br.com.kikuchi.henrique.ecommerceapi.entity.Categoria;
import br.com.kikuchi.henrique.ecommerceapi.entity.Fornecedor;
import br.com.kikuchi.henrique.ecommerceapi.entity.Produto;
import br.com.kikuchi.henrique.ecommerceapi.mapper.ProdutoMapper;
import br.com.kikuchi.henrique.ecommerceapi.service.CategoriaService;
import br.com.kikuchi.henrique.ecommerceapi.service.FornecedorService;
import br.com.kikuchi.henrique.ecommerceapi.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;
    private final CategoriaService categoriaService;
    private final FornecedorService fornecedorService;

    @GetMapping
    public ResponseEntity<Page<Produto>> getAllProdutos(
            @PageableDefault(sort = "preco", direction = Sort.Direction.ASC) Pageable pageable,
            @RequestParam(value = "categorias", required = false) long[] categoriasId){

        if (categoriasId != null){
            return ResponseEntity.ok().body(produtoService.getAllProdutosByCategoriasId(pageable, categoriasId));
        }

        return ResponseEntity.ok().body(produtoService.getAllProdutos(pageable));
    }

//    @GetMapping(/categorias/search)
//    public ResponseEntity<Page<Produto>> getAllProdutosByCategoriasId(
//            @PageableDefault(sort = "preco", direction = Sort.Direction.ASC) Pageable pageable,
//            @RequestParam("categorias") long[] categoriasId){
//        return ResponseEntity.ok().body(produtoService.getAllProdutosByCategoriasId(pageable, categoriasId));
//    }

    @GetMapping(value = "/categorias/{nomeCategoria}")
    public ResponseEntity<Page<Produto>> getAllProdutosByCategoria(
            @PageableDefault(sort = "preco", direction = Sort.Direction.ASC) Pageable pageable, String nomeCategoria){
        return ResponseEntity.ok().body(produtoService.getAllProdutosByCategoria(pageable, nomeCategoria));
    }


    @PostMapping("/create")
    public ResponseEntity<Produto> saveProduto(@RequestBody @Valid ProdutoCreateDto produtoDto){
        Produto produto = ProdutoMapper.INSTANCE.produtoCreateDtoToProduto(produtoDto);
        Fornecedor fornecedor = fornecedorService.getFornecedorByDescricao(produtoDto.fornecedorNome());
        Categoria categoria = categoriaService.getCategoriaByDescricao(produtoDto.categoriaNome());
        produto.setCategoria(categoria);
        produto.setFornecedor(fornecedor);
        return ResponseEntity.ok().body(produtoService.saveProduto(produto));
    }

    @PutMapping("/{produtoId}/novo-preco")
    public ResponseEntity<?> updatePriceProduct(@PathVariable("produtoId") Long produtoId,
                                                @RequestParam("novoPreco") BigDecimal novoPreco){

        produtoService.updatePrecoDoProdutoById(produtoId, novoPreco);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{produtoId}")
    public ResponseEntity<?> deleteProdutoById(@PathVariable("produtoId") Long produtoId) {

        produtoService.deleteProdutoById(produtoId);
        return ResponseEntity.noContent().build();
    }
}
