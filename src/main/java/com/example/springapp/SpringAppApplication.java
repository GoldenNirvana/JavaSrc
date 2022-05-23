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
public class SpringAppApplication {
  public static void main(String[] args) {
    SpringApplication.run(SpringAppApplication.class, args);
  }

  @Bean
  PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  CommandLineRunner run(UserService userService) {
    return args ->
    {
      userService.saveRole(new Role(null, "ROLE_USER"));
      userService.saveRole(new Role(null, "ROLE_ADMIN"));
      userService.saveUser(new User(null, "Ilya Svaikin", "admin", "admin", new ArrayList<>()));
      userService.saveUser(new User(null, "Artem Pyzikov", "user", "user", new ArrayList<>()));
      userService.addRoleToUser("user", "ROLE_USER");
      userService.addRoleToUser("admin", "ROLE_ADMIN");
    };
  }
}
