package com.example.springapp.service;

import com.example.springapp.entity.AutoEntity;
import com.example.springapp.entity.AutoPersonnelEntity;
import com.example.springapp.repository.AutoPersonnelRepo;
import com.example.springapp.repository.AutoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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
    if (autoPersonnel.isPresent())
    {
      AutoEntity auto = new AutoEntity(num, color, mark, autoPersonnel.get());
      System.out.println("Машина добавлена");
      return autoRepo.save(auto);
    }
    throw new RuntimeException();
  }
}
