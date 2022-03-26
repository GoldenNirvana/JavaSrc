package com.example.springapp.controller;

import com.example.springapp.entity.AutoPersonnelEntity;
import com.example.springapp.service.AutoPersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/autoPersonnels")
public class AutoPersonnelController
{

  @Autowired
  private AutoPersonnelService autoPersonnelService;

  @PostMapping
  public ResponseEntity addNewAutoPersonnel(@RequestBody AutoPersonnelEntity auto)
  {
    try
    {
      return ResponseEntity.ok(autoPersonnelService.addNewAutoPersonnel(auto));
    } catch (Exception e)
    {
      return ResponseEntity.badRequest().body("Auto_personnel error");
    }
  }
}
