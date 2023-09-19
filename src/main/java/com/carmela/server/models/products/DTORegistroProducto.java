/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carmela.server.models.products;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 *
 * @author juanchi
 */
public record DTORegistroProducto(
        
        @NotBlank(message = "El nombre es obligatorio")
        String nombre,
        
        @NotBlank(message = "La descripcion es obligatorio")
        String descripcion,
        
        @NotNull(message = "El precio es obligatorio")
        Double precio,
        
        Double precioOferta,

        List<Images> images,
        
        @NotNull(message = "El stock es obligatorio")
        @Min(value = 1, message = "El stock no puede ser menor que 1")
        Integer stock,
        
        @NotNull(message = "La categoria es obligatorio")
        @Valid
        Categoria categoria
        
        ) {
    
}
