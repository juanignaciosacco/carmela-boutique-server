package com.carmela.server.controller;

import com.carmela.server.models.products.*;
import com.carmela.server.services.ProductService;
import jakarta.validation.Valid;

import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@CrossOrigin(origins = "*")
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

    @PostMapping()
    public ResponseEntity<Product> cargarProducto(@RequestBody @Valid DTORegistroProducto dtoRegistroProducto) {
        Product product = new Product(dtoRegistroProducto);
        // Me fijo que las imagenes existan
        if (dtoRegistroProducto.images() != null && !dtoRegistroProducto.images().isEmpty()) {
            List<Images> imagesList = new ArrayList<>();

            // Recorro las imagenes provenientes de dtoRegistrarProducto (RequestBody), les asigno su respectivo producto y su URL
            for (Images img : dtoRegistroProducto.images()) {
                Images image = new Images();
                image.setUrl(img.getUrl());
                image.setProduct(product);
                imagesList.add(image);
        }
            // En producto le asigno la lista imagenes
            product.setImages(imagesList);
        }
        service.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
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
