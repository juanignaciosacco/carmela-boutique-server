/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carmela.server.dto.user;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import java.util.Date;

/**
 *
 * @author juanchi
 */
public record DTORegistroUsuario(
        
        @NotBlank(message = "El nombre es obligatorio")
        String nombre,
        
        @NotBlank(message = "El apellido es obligatorio")
        String apellido,
        
        @NotBlank(message = "El username es obligatorio")
        @Email(message = "Formato incorrecto! Debe ingresar un username valido!")
        @Column(unique = true)
        String username,

        @NotBlank(message = "La contraseña es obligatoria")
        String password,
        
        @NotNull(message = "La fecha de nacimiento es obligatoria")
        @Past(message = "La fecha no puede ser presente o futuro!")
        Date fechaNacimiento,

        @NotBlank(message = "El rol es obligatorio")
        String rol
        
        ) {

}
