package br.com.kikuchi.henrique.ecommerceapi.mapper;

import br.com.kikuchi.henrique.ecommerceapi.dto.ProdutoCreateDto;
import br.com.kikuchi.henrique.ecommerceapi.entity.Produto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProdutoMapper {

    ProdutoMapper INSTANCE = Mappers.getMapper( ProdutoMapper.class );

    Produto produtoCreateDtoToProduto(ProdutoCreateDto produtoCreateDto);
}
