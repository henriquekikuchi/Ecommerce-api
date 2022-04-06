package br.com.kikuchi.henrique.ecommerceapi.mapper;

import br.com.kikuchi.henrique.ecommerceapi.dto.CategoriaCreateDto;
import br.com.kikuchi.henrique.ecommerceapi.entity.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoriaMapper {

    CategoriaMapper INSTANCE = Mappers.getMapper( CategoriaMapper.class );

    Categoria categoriaCreateDtoToCategoria(CategoriaCreateDto categoriaCreateDto);
}
