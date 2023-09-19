/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carmela.server.models.users;

import java.util.Date;

/**
 *
 * @author juanchi
 */
public record DTORespuestaUsuario(
        
        String nombre,
        
        String apellido,
        
        String email,

        Date fechaNacimiento,

        String rol
        
        ) {

}
