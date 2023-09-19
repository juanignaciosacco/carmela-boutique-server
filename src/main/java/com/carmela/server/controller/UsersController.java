/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carmela.server.controller;

import com.carmela.server.models.users.DTORegistroUsuario;
import com.carmela.server.models.users.DTORespuestaUsuario;
import com.carmela.server.models.users.User;
import com.carmela.server.services.UserService;
import jakarta.validation.Valid;
//import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author juanchi
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/usuarios")
public class UsersController {

    @Autowired
    private UserService service;

    @GetMapping
    public List<User> list() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> show(@PathVariable Long id) {
        Optional<User> userOptional = service.findyByID(id);
        if (userOptional.isPresent())
        {
            return ResponseEntity.ok(userOptional.orElseThrow());
        } else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<DTORespuestaUsuario> cargarUsuario(@RequestBody @Valid DTORegistroUsuario dtoRegistroUsuario, UriComponentsBuilder uriComponentsBuilder) {
        User user = service.save(new User(dtoRegistroUsuario));
        DTORespuestaUsuario dtoRespuestaUsuario = new DTORespuestaUsuario(user.getNombre(), 
                user.getApellido(), user.getEmail(), user.getFechaNacimiento(), user.getRol());
//        URI url = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(user.getId()).toUri();
//        return ResponseEntity.created(url).body(dtoRespuestaUsuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoRespuestaUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@RequestBody User user, @PathVariable Long id) {
        Optional<User> userOptional = service.update(user, id);
        if (userOptional.isPresent())
        {
            return ResponseEntity.status(HttpStatus.CREATED).body(userOptional.orElseThrow());
        } else
        {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        Optional<User> userOptional = service.findyByID(id);
        if (userOptional.isPresent())
        {
            service.remove(id);
            return ResponseEntity.noContent().build();
        } else
        {
            return ResponseEntity.notFound().build();
        }
    }
}
