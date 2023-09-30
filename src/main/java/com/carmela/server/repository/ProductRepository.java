package com.carmela.server.repository;

import com.carmela.server.models.product.Product;
import org.springframework.data.repository.CrudRepository;


public interface ProductRepository extends CrudRepository<Product, Long> {

}
