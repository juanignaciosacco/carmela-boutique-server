/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carmela.server.services;

import com.carmela.server.models.users.User;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author juanchi
 */
public interface UserService {
    
    List<User> findAll();
    
    Optional<User> findyByID(Long id);
    
    User save(User user);
    
    Optional<User> update(User user, Long id);
    
    void remove(Long id);
      
}
