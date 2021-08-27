/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.loginmicroservices.controller;

import com.loginmicroservices.model.Login;
import com.loginmicroservices.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Surya Jyoti
 */
@Controller
public class LoginController {
    @Autowired
    private LoginRepository loginRepository;
    RestTemplate restTemplate=new RestTemplate();
    @GetMapping("/")
    public String login()
    {
       return "login";
    }
    @PostMapping("/home")
    public String home(@ModelAttribute("login") Login login,@RequestParam("username") String username,@RequestParam("password") String password,Model model)
    {
      Login n=null;
      try
      {
          n=loginRepository.findByEmailAddress(username);
      }
      catch(Exception e)
      {
         e.printStackTrace();
         System.out.println("User not found !!!!");
      }
      if(n!=null)
      {
         model.addAttribute("password",password);
         return "index";
      }
      model.addAttribute("error","User not found kindly Register !!");
      System.out.println(login);
      return "login";
    }
    @GetMapping("/register")
    public String register()
    {
       return "register";
    }
    @PostMapping("/set-user")
    public String goToRegistaionMicroservice(@RequestParam("username") String username,@RequestParam("password") String password,@RequestParam("password_confirmation") String password_confirmation,Model model)
    {
        System.out.println("Going to Register microservice from login microservice...");
       //code go to registation microservice to register user
        if(password.equals(password_confirmation))
        {
           this.restTemplate.getForObject("http://localhost:8085/register-user/"+username+"/"+password,String.class);
           model.addAttribute("registerSuccess","Successfully Register..,Kindly login to continue");
        }
        else
        {
           model.addAttribute("registerError","Password does not match....");
        }
       System.out.println("Going to Register microservice from login microservice end Here...");
       return "login";
    }
}
