package com.carmela.server.controller;

import com.carmela.server.models.products.Product;
import com.carmela.server.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/productos")
public class ProductosController {

    @Autowired
    private ProductService service;

    @GetMapping
    public List<Product> list(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> show(@PathVariable Long id) {
        Optional<Product> prodOptional = service.findByID(id);
        if (prodOptional.isPresent()) {
            return ResponseEntity.ok(prodOptional.orElseThrow());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Product> cargarProducto(@RequestBody Product product) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(product));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Product product, @PathVariable Long id) {
        Optional<Product> prod = service.update(product, id);
        if (prod.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(prod.orElseThrow());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        Optional<Product> prodOptional = service.findByID(id);
        if (prodOptional.isPresent()) {
            service.remove(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
