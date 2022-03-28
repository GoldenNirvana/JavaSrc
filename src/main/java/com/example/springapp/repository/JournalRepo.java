package com.example.springapp.repository;

import com.example.springapp.entity.AutoEntity;
import com.example.springapp.entity.JournalEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface JournalRepo extends CrudRepository<JournalEntity, Integer>
{
  List<JournalEntity> findJournalEntityByAutoId(AutoEntity autoId);
}
