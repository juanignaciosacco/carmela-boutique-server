/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carmela.server.models.users;

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
        
        @NotBlank(message = "El email es obligatorio")
        @Email
        String email,

        @NotBlank(message = "La contraseña es obligatoria")
        String password,
        
        @NotNull(message = "La fecha de nacimiento es obligatorio")
        @Past(message = "La fecha no puede ser presente o futura!")
        Date fechaNacimiento,

        @NotBlank(message = "El rol es obligatorio")
        String rol
        
        ) {

}
