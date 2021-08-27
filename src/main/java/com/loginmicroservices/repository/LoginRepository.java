/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.loginmicroservices.repository;

import com.loginmicroservices.model.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Surya Jyoti
 */
public interface LoginRepository extends JpaRepository<Login, Integer> {
      @Query(value = "SELECT * FROM login WHERE username = ?1", nativeQuery = true)
      Login findByEmailAddress(String username);
}
