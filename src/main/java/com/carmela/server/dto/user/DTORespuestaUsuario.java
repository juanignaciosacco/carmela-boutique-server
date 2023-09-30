/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carmela.server.dto.user;

import java.util.Date;

/**
 *
 * @author juanchi
 */
public record DTORespuestaUsuario(
        
        String nombre,
        
        String apellido,
        
        String username,

        Date fechaNacimiento,

        String rol
        
        ) {

}
