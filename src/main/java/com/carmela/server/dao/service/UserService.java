/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carmela.server.dao.service;

import com.carmela.server.models.user.User;
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
