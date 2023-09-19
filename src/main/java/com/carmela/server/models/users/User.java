/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carmela.server.models.users;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author juanchi
 */
@Table(name = "Usuarios")
@Entity(name = "Usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    private String email;
    private String password;
    private Date fechaNacimiento;
    private String rol;

    public User(DTORegistroUsuario dtoRegistroUsuario) {
        this.nombre = dtoRegistroUsuario.nombre();
        this.apellido = dtoRegistroUsuario.apellido();
        this.email = dtoRegistroUsuario.email();
        this.password = dtoRegistroUsuario.password();
        this.fechaNacimiento = dtoRegistroUsuario.fechaNacimiento();
        this.rol = dtoRegistroUsuario.rol();
    }
        
}
