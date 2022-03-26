package com.example.springapp.controller;

import com.example.springapp.entity.AutoEntity;
import com.example.springapp.entity.JournalEntity;
import com.example.springapp.entity.RouteEntity;
import com.example.springapp.exception.RouteAlreadyExist;
import com.example.springapp.exception.RouteNotFound;
import com.example.springapp.repository.RouteRepo;
import com.example.springapp.service.AutoService;
import com.example.springapp.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/autos")
public class AutoController
{

  @Autowired
  private AutoService autoService;

  /*@PostMapping("/addNewByBody")
  public ResponseEntity addNewAutoByBody(@RequestBody AutoEntity auto)
  {
    try
    {

      return ResponseEntity.ok(autoService.addNewAuto(auto.getNum(), auto.getColor(), auto.getMark(), auto.getPersonnel_id().getId()));
    } catch (Exception e)
    {
      return ResponseEntity.badRequest().body("Auto error");
    }
  }*/

  @PostMapping("/addNewByParams")
  public ResponseEntity addNewAutoByParams(@RequestParam String num, @RequestParam String color, @RequestParam String mark, @RequestParam Integer personnel_id)
  {
    try
    {
      return ResponseEntity.ok(autoService.addNewAuto(num, color, mark, personnel_id));
    } catch (Exception e)
    {
      return ResponseEntity.badRequest().body("Auto error");
    }
  }

}
