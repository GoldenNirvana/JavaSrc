package com.example.springapp.repository;

import com.example.springapp.entity.JournalEntity;
import org.springframework.data.repository.CrudRepository;

public interface JournalRepo extends CrudRepository<JournalEntity, Integer> {
}
