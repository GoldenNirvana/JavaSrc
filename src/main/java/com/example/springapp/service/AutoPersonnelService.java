package com.example.springapp.service;

import com.example.springapp.entity.AutoPersonnelEntity;
import com.example.springapp.repository.AutoPersonnelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutoPersonnelService
{
  @Autowired
  private AutoPersonnelRepo autoPersonnelRepo;

  public AutoPersonnelEntity addNewAutoPersonnel(AutoPersonnelEntity personnel) throws RuntimeException
  {
    Optional<AutoPersonnelEntity> person = autoPersonnelRepo.findByFirstNameAndLastNameAndPatherName(personnel.getFirstName(), personnel.getLastName(), personnel.getPatherName());
    if (person.isPresent())
    {
      throw new RuntimeException("Водитель уже существует!");
    }
    return autoPersonnelRepo.save(personnel);
  }

  public Boolean deleteAll()
  {
    autoPersonnelRepo.deleteAll();
    return true;
  }

  public Boolean deleteById(Integer id)
  {
    autoPersonnelRepo.deleteById(id);
    return true;
  }
}
