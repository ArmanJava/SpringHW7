package ru.geekbrains.homework7.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.homework7.entity.Product;

import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    @Override
    Iterable<Product> findAll();

    @Override
    Optional<Product> findById(Long id);

    @Override
    <S extends Product> S save(S s);

    @Override
    void deleteById(Long id);

    @Override
    void delete(Product product);
}
