package ru.geekbrains.homework7.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.homework7.DTO.ProductDTO;
import ru.geekbrains.homework7.constants.Constants;
import ru.geekbrains.homework7.entity.Product;
import ru.geekbrains.homework7.repository.ProductRepository;
import ru.geekbrains.homework7.utils.Converter;
import ru.geekbrains.homework7.utils.BadRequestException;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ProductService {
    private final Converter converter;
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAll() {
        return (List<Product>) productRepository.findAll();
    }

    public Optional<Product> getById(Long id) {
        return productRepository.findById(id);
    }

    public Product save(Product product) {
        product.setId(0L);
        return productRepository.save(product);
    }

    public Product update(Product product) {
        if (product.getId() == null) {
            throw new BadRequestException(Constants.NOTHING_TO_UPDATE);
        }
        if (productRepository.findById(product.getId()).orElse(null) == null) {
            throw new BadRequestException(Constants.NOTHING_TO_UPDATE);
        }
        return productRepository.save(product);
    }

    public void delete(Product product) {
        if (product.getId() == null) {
            throw new BadRequestException(Constants.NOTHING_TO_DELETE);
        }
        if (productRepository.findById(product.getId()).orElse(null) == null) {
            throw new BadRequestException(Constants.NOTHING_TO_DELETE);
        }
        productRepository.deleteById(product.getId());
    }

    public void saveOneProduct(ProductDTO productDTO) {
        productRepository.save(converter.convertDtoToEntity(productDTO));

    }
}
