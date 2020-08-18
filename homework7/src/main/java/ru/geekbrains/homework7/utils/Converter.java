package ru.geekbrains.homework7.utils;

import org.springframework.stereotype.Component;
import ru.geekbrains.homework7.DTO.ProductDTO;
import ru.geekbrains.homework7.entity.Product;

@Component
public class Converter {
   public Product convertDtoToEntity(ProductDTO productDTO){

       return Product.builder()
               .cost(productDTO.getCost())
               .title(productDTO.getTitle())
               .build();


   }
}
