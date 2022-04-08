package br.com.kikuchi.henrique.ecommerceapi.service;

import br.com.kikuchi.henrique.ecommerceapi.entity.Fornecedor;
import br.com.kikuchi.henrique.ecommerceapi.exception.FornecedorNotFound;
import br.com.kikuchi.henrique.ecommerceapi.repository.FornecedorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FornecedorServiceImpl implements FornecedorService {

    private final FornecedorRepository fornecedorRepository;

    @Override
    public List<Fornecedor> getAllFornecedores() {
        return fornecedorRepository.findAll();
    }

    @Override
    public Fornecedor getFornecedorById(Long id) {
        return fornecedorRepository.findById(id).orElseThrow(FornecedorNotFound::new);
    }

    @Override
    public Fornecedor getFornecedorByDescricao(String descricao) {
        return fornecedorRepository.findByDescricao(descricao).orElseThrow(FornecedorNotFound::new);
    }

    @Override
    public Fornecedor saveFornecedor(Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }

    @Override
    public void updateFornecedor(Long id, Fornecedor fornecedor) {
        Fornecedor fornecedor1 = getFornecedorById(id);
        fornecedor1.setDescricao(fornecedor.getDescricao());
        fornecedorRepository.saveAndFlush(fornecedor1);
    }

    @Override
    public void deleteFornecedorById(Long id) {
        getFornecedorById(id);
        fornecedorRepository.deleteById(id);
    }
}
