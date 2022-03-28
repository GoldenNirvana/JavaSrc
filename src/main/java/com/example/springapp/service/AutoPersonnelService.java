package com.example.springapp.service;

import com.example.springapp.entity.AutoPersonnelEntity;
import com.example.springapp.repository.AutoPersonnelRepo;
import com.example.springapp.repository.AutoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutoPersonnelService
{
  @Autowired
  private AutoPersonnelRepo autoPersonnelRepo;

  @Autowired
  private AutoRepo autoRepo;

  public AutoPersonnelEntity addNewAutoPersonnel(AutoPersonnelEntity personnel) throws RuntimeException
  {
    Optional<AutoPersonnelEntity> person = autoPersonnelRepo.findByFirstNameAndLastNameAndPatherName(personnel.getFirstName(), personnel.getLastName(), personnel.getPatherName());
    if (person.isPresent())
    {
      throw new RuntimeException("Водитель уже существует!");
    }
    return autoPersonnelRepo.save(personnel);
  }

  public Boolean deleteById(Integer id)
  {
    Optional<AutoPersonnelEntity> autoPersonnel = autoPersonnelRepo.findById(id);
    if (autoPersonnel.isPresent())
    {
      autoPersonnelRepo.deleteById(id);
      return true;
    }
    return false;
  }

  public AutoPersonnelEntity newName(Integer id, String newName)
  {
    Optional<AutoPersonnelEntity> personnel = autoPersonnelRepo.findById(id);

    if (!personnel.isPresent())
    {
      throw new RuntimeException("Такого водителя нет в базе");
    }


    Optional<AutoPersonnelEntity> personnelEntity = autoPersonnelRepo.findByFirstNameAndLastNameAndPatherName(newName, personnel.get().getLastName(), personnel.get().getPatherName());

    if (personnelEntity.isPresent())
    {
      throw new RuntimeException("Водитель с такими ФИО уже записан");


    }
    personnel.get().setFirstName(newName);
    return autoPersonnelRepo.save(personnel.get());
  }
}
