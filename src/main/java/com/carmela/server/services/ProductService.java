package com.carmela.server.services;

import com.carmela.server.models.products.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll();

    Optional<Product> findByID(Long id);

    Product save(Product product);

    Optional<Product> update(Product product, Long id);

    void remove(Long id);
}
