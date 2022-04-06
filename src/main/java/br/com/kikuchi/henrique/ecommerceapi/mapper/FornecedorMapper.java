package br.com.kikuchi.henrique.ecommerceapi.mapper;

import br.com.kikuchi.henrique.ecommerceapi.dto.FornecedorCreateDto;
import br.com.kikuchi.henrique.ecommerceapi.entity.Fornecedor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FornecedorMapper {

    FornecedorMapper INSTANCE = Mappers.getMapper( FornecedorMapper.class );

    Fornecedor fornecedorCreateDtoToFornecedor(FornecedorCreateDto fornecedorCreateDto);
}
