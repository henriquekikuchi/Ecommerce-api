package br.com.kikuchi.henrique.ecommerceapi.service;

import br.com.kikuchi.henrique.ecommerceapi.dto.CarrinhoDeCompraResponseDTO;
import br.com.kikuchi.henrique.ecommerceapi.entity.CarrinhoDeCompra;
import br.com.kikuchi.henrique.ecommerceapi.entity.CarrinhoDeCompraStatus;
import br.com.kikuchi.henrique.ecommerceapi.entity.Produto;
import br.com.kikuchi.henrique.ecommerceapi.entity.User;
import br.com.kikuchi.henrique.ecommerceapi.exception.CarrinhoDeCompraNotFound;
import br.com.kikuchi.henrique.ecommerceapi.repository.CarrinhoDeCompraRepository;
import br.com.kikuchi.henrique.ecommerceapi.repository.ProdutoRepository;
import br.com.kikuchi.henrique.ecommerceapi.repository.UserRepository;
import br.com.kikuchi.henrique.ecommerceapi.security.ApplicationUserRole;
import br.com.kikuchi.henrique.ecommerceapi.security.UserSecurity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarrinhoDeCompraServiceImpl implements CarrinhoDeCompraService {

    private final CarrinhoDeCompraRepository carrinhoDeCompraRepository;
    private final ProdutoRepository produtoRepository;

    @Override
    @PreAuthorize("hasAnyRole('CLIENT','ADMIN')")
    public Page<CarrinhoDeCompraResponseDTO> getAllCarrinhoDeCompra(Pageable pageable) {
        if (getRoleOfUserContext().equals(ApplicationUserRole.CLIENT)){
            return carrinhoDeCompraRepository
                    .findAllCarrinhosDeCompraByCliente_Username(pageable,getUserOfContext().getUsername());
        }
        return carrinhoDeCompraRepository.findAllCarrinhosDeCompra(pageable);
    }

    @Override
    @PreAuthorize("hasAnyRole('CLIENT','ADMIN')")
    public CarrinhoDeCompra getCarrinhoDeCompraById(Long id) {
        if (getRoleOfUserContext().equals(ApplicationUserRole.CLIENT)) {
            return carrinhoDeCompraRepository
                    .findCarrinhoDeCompraByIdAndCliente_Username(id, getUserOfContext().getUsername())
                    .orElseThrow(CarrinhoDeCompraNotFound::new);
        }
        return carrinhoDeCompraRepository.findById(id).orElseThrow(CarrinhoDeCompraNotFound::new);
    }

    @Override
    @PreAuthorize("hasRole('CLIENT')")
    public CarrinhoDeCompra saveCarrinhoDeCompra(List<Long> idsProdutos) {
        CarrinhoDeCompra carrinhoDeCompra = null;
        List<Produto> produtos = produtoRepository.findAllById(idsProdutos);
        User user = getUserOfContext();
        if (!hasUserCarrinhoDeCompraEmAberto(user.getUsername())) {
            carrinhoDeCompra = CarrinhoDeCompra.builder()
                    .cliente(user)
                    .produtos(produtos)
                    .status(CarrinhoDeCompraStatus.EM_ABERTO)
                    .build();
            return carrinhoDeCompraRepository.save(carrinhoDeCompra);
        }
        return carrinhoDeCompra;
    }

    @Override
    @PreAuthorize("hasRole('CLIENT')")
    public void realizarCarrinhoDeCompra(Long id) {
        CarrinhoDeCompra carrinhoDeCompra = updateStatus(getCarrinhoDeCompraById(id),
                CarrinhoDeCompraStatus.REALIZADO);
        carrinhoDeCompraRepository.saveAndFlush(carrinhoDeCompra);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void encerraCarrinhoDeCompra(Long id) {
        CarrinhoDeCompra carrinhoDeCompra = updateStatus(getCarrinhoDeCompraById(id),
                CarrinhoDeCompraStatus.CONCLUIDO);
        carrinhoDeCompraRepository.saveAndFlush(carrinhoDeCompra);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void entregueCarrinhoDeCompra(Long id) {
        CarrinhoDeCompra carrinhoDeCompra = updateStatus(getCarrinhoDeCompraById(id),
                CarrinhoDeCompraStatus.ENTREGUE);
        carrinhoDeCompraRepository.saveAndFlush(carrinhoDeCompra);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void cancelarCarrinhoDeCompra(Long id) {
        CarrinhoDeCompra carrinhoDeCompra = updateStatus(getCarrinhoDeCompraById(id),
                CarrinhoDeCompraStatus.CANCELADO);
        carrinhoDeCompraRepository.saveAndFlush(carrinhoDeCompra);
    }

    @Override
    @PreAuthorize("hasRole('CLIENT')")
    public void addProdutoAoCarrinhoDeCompra(Long id, Produto produto) {
        CarrinhoDeCompra carrinhoDeCompra = getCarrinhoDeCompraById(id);
        carrinhoDeCompra.getProdutos().add(produto);
        carrinhoDeCompraRepository.saveAndFlush(carrinhoDeCompra);
    }

    @Override
    @PreAuthorize("hasRole('CLIENT')")
    public void addProdutoAoCarrinhoDeCompra(Long id, Produto... produtos) {
        CarrinhoDeCompra carrinhoDeCompra = getCarrinhoDeCompraById(id);
        carrinhoDeCompra.getProdutos().addAll(List.of(produtos));
        carrinhoDeCompraRepository.saveAndFlush(carrinhoDeCompra);
    }

    @Override
    @PreAuthorize("hasRole('CLIENT')")
    public void removeProdutoDoCarrinhoDeCompra(Long id, Produto produto) {
        CarrinhoDeCompra carrinhoDeCompra = getCarrinhoDeCompraById(id);
        List<Produto> produtos = carrinhoDeCompra.getProdutos().stream()
                .filter(produto1 -> produto.getNome() != produto1.getNome())
                .collect(Collectors.toList());
        carrinhoDeCompra.setProdutos(produtos);
        carrinhoDeCompraRepository.saveAndFlush(carrinhoDeCompra);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void removeCarrinhoDeCompra(Long id) {
        getCarrinhoDeCompraById(id);
        carrinhoDeCompraRepository.deleteById(id);
    }

    private boolean hasUserCarrinhoDeCompraEmAberto(String username) {
        return carrinhoDeCompraRepository.findCarrinhoDeCompraEmAbertoByClientUsername(username)
                .isPresent();
    }

    private CarrinhoDeCompra updateStatus(CarrinhoDeCompra carrinhoDeCompra,
                                          CarrinhoDeCompraStatus novoStatus){
        carrinhoDeCompra.setStatus(novoStatus);
        return carrinhoDeCompra;
    }

    private ApplicationUserRole getRoleOfUserContext(){
        User user = (User) ((UserSecurity) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUser();
        return user.getRole();
    }

    private User getUserOfContext(){
        return (User) ((UserSecurity) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal()).getUser();
    }
}
