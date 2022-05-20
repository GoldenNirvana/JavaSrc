package com.example.springapp;

import com.example.springapp.entity.Role;
import com.example.springapp.entity.User;
import com.example.springapp.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class SpringAppApplication
{
  public static void main(String[] args)
  {
    SpringApplication.run(SpringAppApplication.class, args);
  }

  @Bean
  PasswordEncoder passwordEncoder()
  {
    return new BCryptPasswordEncoder();
  }

  @Bean
  CommandLineRunner run(UserService userService)
  {
    return args ->
    {
      userService.saveRole(new Role(null, "ROLE_USER"));
      userService.saveRole(new Role(null, "ROLE_ADMIN"));

      userService.saveUser(new User(null, "Depp Johny", "johny", "12345", new ArrayList<>()));
      userService.saveUser(new User(null, "Moss Kate", "mossK", "12345", new ArrayList<>()));
      userService.saveUser(new User(null, "Averdin Kate", "kate123", "12345", new ArrayList<>()));
      userService.saveUser(new User(null, "Manner Sam", "sam", "12345", new ArrayList<>()));
      userService.saveUser(new User(null, "Red Mary", "marY", "12345", new ArrayList<>()));

      userService.addRoleToUser("johny", "ROLE_USER");
      userService.addRoleToUser("mossK", "ROLE_USER");
      userService.addRoleToUser("kate123", "ROLE_USER");
      userService.addRoleToUser("sam", "ROLE_ADMIN");
      userService.addRoleToUser("marY", "ROLE_ADMIN");
    };
  }
}
