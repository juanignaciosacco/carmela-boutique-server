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
        
        @NotBlank
        String nombre,
        
        @NotBlank
        String apellido,
        
        @NotBlank
        @Email
        String email,
        
        @NotNull
        @Past(message = "La fecha no puede ser presente o futura!")
        Date fechaNacimiento
        
        ) {

}
