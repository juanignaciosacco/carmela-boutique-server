package com.carmela.server.controller;

import com.carmela.server.dto.product.DTORegistroProducto;
import com.carmela.server.dto.product.DTORespuestaProducto;
import com.carmela.server.models.product.*;
import com.carmela.server.dao.service.ProductService;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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

    @PostMapping
    public ResponseEntity<?> cargarProducto(@RequestBody @Valid DTORegistroProducto dtoRegistroProducto, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
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
        DTORespuestaProducto dtoRespuestaProducto = new DTORespuestaProducto(product.getNombre(), product.getDescripcion(), product.getPrecio(),
                product.getPrecioOferta(), product.getImages(), product.getStock(), product.getCategoria());
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoRespuestaProducto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Product product, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return validation(result);
        }
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

    private ResponseEntity<?> validation(BindingResult result) {
        HashMap<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
