package com.carmela.server.services;

import com.carmela.server.models.products.Product;
import com.carmela.server.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Product> findAll() {
        return (List<Product>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Product> findByID(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    @Transactional
    public Optional<Product> update(Product product, Long id) {
       Optional<Product> prod = this.findByID(id);
       Product prodOptional = null;
       if (prod.isPresent()) {
           Product prodDb = prod.orElseThrow();
           prodDb.setCategoria(product.getCategoria());
           prodDb.setImages(product.getImages());
           prodDb.setDescripcion(product.getDescripcion());
           prodDb.setNombre(product.getNombre());
           prodDb.setPrecioOferta(product.getPrecioOferta());
           prodDb.setPrecio(product.getPrecio());
           prodDb.setStock(product.getStock());
           prodOptional = this.save(prodDb);
       }
       return Optional.ofNullable(prodOptional);
    }

    @Override
    @Transactional
    public void remove(Long id) {
        repository.deleteById(id);
    }


}
