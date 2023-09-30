package com.carmela.server.dao.service;

import com.carmela.server.models.product.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll();

    Optional<Product> findByID(Long id);

    Product save(Product product);

    Optional<Product> update(Product product, Long id);

    void remove(Long id);
}
