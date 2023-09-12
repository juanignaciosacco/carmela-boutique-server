/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carmela.server.services;

import com.carmela.server.models.users.User;
import com.carmela.server.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author juanchi
 */
@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository repository;
    
    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return (List<User>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findyByID(Long id) {
        return repository.findById(id); 
    }

    @Override
    @Transactional
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public Optional<User> update(User user, Long id) {   
        Optional<User> usu = this.findyByID(id);
        User userOptional = null;
        if (usu.isPresent()) {
            User userDb = usu.orElseThrow();
            userDb.setNombre(user.getNombre());
            userDb.setApellido(user.getApellido());
            userDb.setEmail(user.getEmail());
            userDb.setFechaNacimiento(user.getFechaNacimiento());
            userOptional = this.save(userDb);
        }
        return Optional.ofNullable(userOptional);  
    }

    @Override
    public void remove(Long id) {
        repository.deleteById(id);
    }
  
    
}
