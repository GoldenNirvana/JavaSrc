package com.example.springapp.service;

import com.example.springapp.entity.AutoEntity;
import com.example.springapp.entity.AutoPersonnelEntity;
import com.example.springapp.repository.AutoPersonnelRepo;
import com.example.springapp.repository.AutoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutoPersonnelService
{
  @Autowired
  private AutoPersonnelRepo autoPersonnelRepo;

  public AutoPersonnelEntity addNewAutoPersonnel(AutoPersonnelEntity auto)
  {
    return autoPersonnelRepo.save(auto);
  }
}
