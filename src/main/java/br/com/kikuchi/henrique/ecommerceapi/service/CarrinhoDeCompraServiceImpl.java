package br.com.kikuchi.henrique.ecommerceapi.service;

import br.com.kikuchi.henrique.ecommerceapi.entity.CarrinhoDeCompra;
import br.com.kikuchi.henrique.ecommerceapi.entity.CarrinhoDeCompraStatus;
import br.com.kikuchi.henrique.ecommerceapi.entity.Produto;
import br.com.kikuchi.henrique.ecommerceapi.entity.User;
import br.com.kikuchi.henrique.ecommerceapi.exception.CarrinhoDeCompraNotFound;
import br.com.kikuchi.henrique.ecommerceapi.exception.UserNotFound;
import br.com.kikuchi.henrique.ecommerceapi.repository.CarrinhoDeCompraRepository;
import br.com.kikuchi.henrique.ecommerceapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarrinhoDeCompraServiceImpl implements CarrinhoDeCompraService {

    private final CarrinhoDeCompraRepository carrinhoDeCompraRepository;
    private final UserRepository userRepository;

    @Override
    public List<CarrinhoDeCompra> getAllCarrinhoDeCompra() {
        return carrinhoDeCompraRepository.findAll();
    }

    @Override
    public CarrinhoDeCompra getCarrinhoDeCompraById(Long id) {
        return carrinhoDeCompraRepository.findById(id).orElseThrow(CarrinhoDeCompraNotFound::new);
    }

    @Override
    public CarrinhoDeCompra saveCarrinhoDeCompra(String username) {
        CarrinhoDeCompra carrinhoDeCompra = null;
        if (!hasUserCarrinhoDeCompraRealizado(username)) {
            User user = userRepository.findUserByUsername(username).orElseThrow(UserNotFound::new);
            carrinhoDeCompra = CarrinhoDeCompra.builder()
                    .cliente(user)
                    .status(CarrinhoDeCompraStatus.EM_ABERTO)
                    .build();
            return carrinhoDeCompraRepository.save(carrinhoDeCompra);
        }
        return carrinhoDeCompra;
    }

    @Override
    public boolean hasUserCarrinhoDeCompraRealizado(String username) {
        return carrinhoDeCompraRepository.findCarrinhoDeCompraRealizadoByClientUsername(username)
                .isPresent();
    }

    @Override
    public void encerraCarrinhoDeCompra(Long id) {
        CarrinhoDeCompra carrinhoDeCompra = updateStatus(getCarrinhoDeCompraById(id), CarrinhoDeCompraStatus.CONCLUIDO);
        carrinhoDeCompraRepository.saveAndFlush(carrinhoDeCompra);
    }

    @Override
    public void entregueCarrinhoDeCompra(Long id) {
        CarrinhoDeCompra carrinhoDeCompra = updateStatus(getCarrinhoDeCompraById(id), CarrinhoDeCompraStatus.ENTREGUE);
        carrinhoDeCompraRepository.saveAndFlush(carrinhoDeCompra);
    }

    @Override
    public void addProdutoAoCarrinhoDeCompra(Long id, Produto produto) {
        CarrinhoDeCompra carrinhoDeCompra = getCarrinhoDeCompraById(id);
        carrinhoDeCompra.getProdutos().add(produto);
        carrinhoDeCompraRepository.saveAndFlush(carrinhoDeCompra);
    }

    @Override
    public void addProdutoAoCarrinhoDeCompra(Long id, Produto... produtos) {
        CarrinhoDeCompra carrinhoDeCompra = getCarrinhoDeCompraById(id);
        carrinhoDeCompra.getProdutos().addAll(List.of(produtos));
        carrinhoDeCompraRepository.saveAndFlush(carrinhoDeCompra);
    }

    @Override
    public void removeProdutoDoCarrinhoDeCompra(Long id, Produto produto) {
        CarrinhoDeCompra carrinhoDeCompra = getCarrinhoDeCompraById(id);
        List<Produto> produtos = carrinhoDeCompra.getProdutos().stream()
                .filter(produto1 -> produto.getNome() != produto1.getNome())
                .collect(Collectors.toList());
        carrinhoDeCompra.setProdutos(produtos);
        carrinhoDeCompraRepository.saveAndFlush(carrinhoDeCompra);
    }

    private CarrinhoDeCompra updateStatus(CarrinhoDeCompra carrinhoDeCompra, CarrinhoDeCompraStatus novoStatus){
        carrinhoDeCompra.setStatus(novoStatus);
        return carrinhoDeCompra;
    }
}
