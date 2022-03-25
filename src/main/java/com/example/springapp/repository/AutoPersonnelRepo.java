package com.example.springapp.repository;

import com.example.springapp.entity.AutoPersonnelEntity;
import org.springframework.data.repository.CrudRepository;

public interface AutoPersonnelRepo extends CrudRepository<AutoPersonnelEntity, Integer> {
}
