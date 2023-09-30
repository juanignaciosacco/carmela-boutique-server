/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carmela.server.controller;

import com.carmela.server.dto.user.DTORegistroUsuario;
import com.carmela.server.dto.user.DTORespuestaUsuario;
import com.carmela.server.models.user.User;
import com.carmela.server.dao.service.UserService;
import jakarta.validation.Valid;
//import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public ResponseEntity<?> cargarUsuario(@RequestBody @Valid DTORegistroUsuario dtoRegistroUsuario, BindingResult result) {
        if (result.hasErrors()) {
            return validation(result);
        }
        User user = service.save(new User(dtoRegistroUsuario));
        DTORespuestaUsuario dtoRespuestaUsuario = new DTORespuestaUsuario(user.getNombre(),
                user.getApellido(), user.getUsername(), user.getFechaNacimiento(), user.getRol());
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoRespuestaUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid DTORegistroUsuario dtoRegistroUsuario,BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return validation(result);
        }
        Optional<User> userOptional = service.update(new User(dtoRegistroUsuario), id);
        if (userOptional.isPresent())
        {
            User usu = new User(dtoRegistroUsuario);
            DTORespuestaUsuario dtoRespuestaUsuario = new DTORespuestaUsuario(usu.getNombre(), usu.getApellido(), usu.getUsername(), usu.getFechaNacimiento(), usu.getRol());
            return ResponseEntity.status(HttpStatus.CREATED).body(dtoRespuestaUsuario);
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

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(err -> {
            errors.put(err.getField(), err.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
