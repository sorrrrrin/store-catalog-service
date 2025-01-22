package com.store.catalog.mappers;

import com.store.catalog.dtos.ProductDto;
import com.store.catalog.entities.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto productToProductDto(Product product);

    Product productDtoToProduct(ProductDto productDto);
}