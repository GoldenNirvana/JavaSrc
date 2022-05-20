package com.example.springapp.repository;

import com.example.springapp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer>
{
  Role findByName(String name);
}
