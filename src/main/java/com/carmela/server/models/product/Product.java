package com.carmela.server.models.product;

import com.carmela.server.dto.product.DTORegistroProducto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import lombok.*;

import java.util.List;

@Table(name = "productos")
@Entity
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
    private Integer stock;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    // Relacion con Imagenes
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL)
    // Esta anotacion es para evitar la Recursion Infinita (Spring no puede serializar a formato JSON)
    @JsonIgnoreProperties("product")
    List<Images> images;

    public Product(DTORegistroProducto dtoRegistroProducto) {
        this.nombre = dtoRegistroProducto.nombre();
        this.descripcion = dtoRegistroProducto.descripcion();
        this.precio = dtoRegistroProducto.precio();
        this.precioOferta = dtoRegistroProducto.precioOferta();
        this.images = dtoRegistroProducto.images();
        this.stock = dtoRegistroProducto.stock();
        this.categoria = dtoRegistroProducto.categoria();
    }
}
