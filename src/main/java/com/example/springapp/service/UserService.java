package com.example.springapp.service;


import com.example.springapp.entity.Role;
import com.example.springapp.entity.User;

import java.util.List;

public interface UserService
{
  User saveUser(User user);

  Role saveRole(Role role);

  void addRoleToUser(String username, String roleName) throws Exception;

  User getUser(String username) throws Exception;

  List<User> getUsers();
}
