package com.store.catalog.mappers;

import com.store.catalog.dtos.ProductDto;
import com.store.catalog.dtos.elastic.ElasticProductDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ElasticMapper {

    ElasticProductDto productDtoToElasticProductDto(ProductDto productDto);

    ProductDto elasticProductDtoToProductDto(ElasticProductDto elasticProductDto);
}
