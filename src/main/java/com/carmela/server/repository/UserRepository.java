/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carmela.server.repository;

import com.carmela.server.models.users.User;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author juanchi
 */
public interface UserRepository extends CrudRepository<User, Long> {
    
}
