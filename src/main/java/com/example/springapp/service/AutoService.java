package com.example.springapp.service;

import com.example.springapp.entity.AutoEntity;
import com.example.springapp.entity.AutoPersonnelEntity;
import com.example.springapp.entity.RouteEntity;
import com.example.springapp.model.Auto;
import com.example.springapp.repository.AutoPersonnelRepo;
import com.example.springapp.repository.AutoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutoService
{

  @Autowired
  private AutoRepo autoRepo;

  @Autowired
  private AutoPersonnelRepo autoPersonnelRepo;

  public AutoEntity addNewAuto(String num, String color, String mark, Integer personnel_id)
  {
    Optional<AutoPersonnelEntity> autoPersonnel = autoPersonnelRepo.findById(personnel_id);

    Optional<AutoEntity> auto = autoRepo.findByNum(num);

    if (auto.isPresent())
    {
      throw new RuntimeException("Машина с таким номером уже зарегестрирована");
    }

    auto = autoRepo.findByPersonnelId(autoPersonnelRepo.findById(personnel_id));

    if (auto.isPresent())
    {
      throw new RuntimeException("Водитель закреплён за другой машиной");
    }

    if (autoPersonnel.isPresent())
    {
      AutoEntity autoEntity = new AutoEntity(num, color, mark, autoPersonnel.get());
      System.out.println("Машина добавлена");
      return autoRepo.save(autoEntity);
    }
    throw new RuntimeException("Пользователь не найден");
  }

  public Boolean deleteById(Integer id)
  {
    Optional<AutoEntity> auto = autoRepo.findById(id);
    if (auto.isPresent())
    {
      autoRepo.deleteById(id);
      return true;
    }
    return false;
  }

  public List<AutoEntity> getAllAutos()
  {
    return (List<AutoEntity>) autoRepo.findAll();
  }
}
