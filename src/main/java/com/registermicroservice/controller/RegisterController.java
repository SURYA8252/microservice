/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.registermicroservice.controller;

import com.registermicroservice.model.Login;
import com.registermicroservice.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Surya Jyoti
 */
@Controller
public class RegisterController {
    @Autowired
    private LoginRepository loginRepository;
    @GetMapping("/check")
    public String registerCheck()
    {
       return "Successfully....";
    }
    @GetMapping("/register-user/{username}/{password}")
    @ResponseBody
    public String registerUser(@PathVariable("username") String username,@PathVariable("password") String password)
    {
        System.out.println("..............In to Register microservice from login microservice...");
        Login login=new Login();
        
        login.setUsername(username);
        login.setPassword(password);
        this.loginRepository.save(login);
        System.out.println("............In Register microservice from login microservice end...");
       return "Register is Successfully";
    }
}
