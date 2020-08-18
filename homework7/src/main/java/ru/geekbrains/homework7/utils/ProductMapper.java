package ru.geekbrains.homework7.utils;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.geekbrains.homework7.DTO.ProductDTO;
import ru.geekbrains.homework7.entity.Product;

import java.util.List;

@Mapper
public interface ProductMapper {

    ProductMapper MAPPER = Mappers.getMapper(ProductMapper.class);

    Product toProductDTO(ProductDTO productDTO);
    @InheritInverseConfiguration
    ProductDTO fromEntity(Product product);

    List<Product> toProductList(List<ProductDTO> productDTOS);
}
