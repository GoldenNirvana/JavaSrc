package com.example.springapp.controller;

import com.example.springapp.entity.AutoEntity;
import com.example.springapp.entity.AutoPersonnelEntity;
import com.example.springapp.service.AutoPersonnelService;
import com.example.springapp.service.AutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/autos")
public class AutoController
{

  @Autowired
  private AutoService autoService;

  @Autowired
  private AutoPersonnelService autoPersonnelService;


  // POST

  @PostMapping("/addNewByBody")
  public ResponseEntity addNewAutoByBody(@RequestBody AutoEntity auto)
  {
    AutoPersonnelEntity autoPersonnelEntity = null;
    try
    {
      autoPersonnelEntity = autoPersonnelService.addNewAutoPersonnel(auto.getPersonnelId());
      return ResponseEntity.ok(autoService.addNewAuto(auto.getNum(), auto.getColor(), auto.getMark(), auto.getPersonnelId().getId()));

    } catch (Exception e)
    {
      if (autoPersonnelEntity != null)
      {
        autoPersonnelService.deleteById(autoPersonnelEntity.getId());
      }
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PostMapping("/addNewByParams")
  public ResponseEntity addNewAutoByParams(@RequestParam String num, @RequestParam String color, @RequestParam String mark, @RequestParam Integer personnel_id)
  {
    try
    {
      return ResponseEntity.ok(autoService.addNewAuto(num, color, mark, personnel_id));
    } catch (Exception e)
    {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  // Get

  @GetMapping("/getAllAutos")
  public ResponseEntity getAllAutos()
  {
    return ResponseEntity.ok(autoService.getAllAutos());
  }

  // Delete

  @DeleteMapping("/deleteById")
  public ResponseEntity deleteById(@RequestParam Integer id)
  {
    try
    {
      return ResponseEntity.ok(autoService.deleteById(id));
    } catch (Exception e)
    {
      return ResponseEntity.badRequest().body("Auto delete error");
    }
  }

}
