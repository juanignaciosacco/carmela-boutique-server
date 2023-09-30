/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carmela.server.dto.product;

import com.carmela.server.models.product.Categoria;
import com.carmela.server.models.product.Images;
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
        
        @NotBlank(message = "La descripcion es obligatoria")
        String descripcion,
        
        @NotNull(message = "El precio es obligatorio")
        Double precio,
        
        Double precioOferta,

        List<Images> images,
        
        @NotNull(message = "El stock es obligatorio")
        @Min(value = 1, message = "El stock no puede ser menor que 1")
        Integer stock,
        
        @NotNull(message = "La categoria es obligatoria")
        @Valid
        Categoria categoria
        
        ) {
    
}
