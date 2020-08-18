package ru.geekbrains.homework7.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.homework7.DTO.ProductDTO;
import ru.geekbrains.homework7.entity.Product;
import ru.geekbrains.homework7.service.ProductService;
import ru.geekbrains.homework7.utils.BadRequestException;
import ru.geekbrains.homework7.utils.MessageResponse;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("products")
public class ProductRestController {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getOneProduct(@PathVariable("id") Long id) {
        return ResponseEntity.of(productService.getById(id));
    }

    @PostMapping
    public Product createNewProduct(@RequestBody Product product) {
        return productService.save(product);
    }
    @PostMapping
    public void  createOneProduct(@RequestBody ProductDTO productDTO) {
         productService.saveOneProduct(productDTO);
    }
    @PutMapping
    public Product updateProduct(@RequestBody Product product) {
        return productService.update(product);
    }

    @DeleteMapping
    public ResponseEntity<MessageResponse> deleteProduct(@RequestBody Product product) {
        productService.delete(product);
        MessageResponse message = new MessageResponse();
        message.setMessage("Успешно удалено");
        message.setTimestamp(System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }


    @ExceptionHandler
    public ResponseEntity<MessageResponse> handleBadRequestException(BadRequestException e) {
        MessageResponse errorResponse = new MessageResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setTimestamp(System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }
}
