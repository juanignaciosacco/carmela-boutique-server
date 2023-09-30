/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carmela.server.dto.product;

import com.carmela.server.models.product.Categoria;
import com.carmela.server.models.product.Images;

import java.util.List;

/**
 *
 * @author juanchi
 */
public record DTORespuestaProducto(

        String nombre,
        String descripcion,
        Double precio,
        Double precioOferta,
        List<Images> images,
        Integer stock,
        Categoria categoria
        
        ) {

}
