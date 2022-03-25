package com.example.springapp.repository;

import com.example.springapp.entity.AutoEntity;
import org.springframework.data.repository.CrudRepository;

public interface AutoRepo extends CrudRepository<AutoEntity, Integer> {
}
