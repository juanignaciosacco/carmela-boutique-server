package com.carmela.server.models;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "productos")
@Entity(name = "Producto")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String descripcion;
    private Double precio;
    private Double precioOferta;
    private String img;
    private Integer stock;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

}
