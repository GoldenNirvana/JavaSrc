package com.example.springapp.controller;

import com.example.springapp.entity.AutoEntity;
import com.example.springapp.entity.AutoPersonnelEntity;
import com.example.springapp.entity.JournalEntity;
import com.example.springapp.entity.RouteEntity;
import com.example.springapp.exception.RouteAlreadyExist;
import com.example.springapp.service.AutoPersonnelService;
import com.example.springapp.service.AutoService;
import com.example.springapp.service.JournalService;
import com.example.springapp.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping("/journals")
public class JournalController
{
  @Autowired
  private AutoPersonnelService autoPersonnelService;

  @Autowired
  private JournalService journalService;

  @Autowired
  private AutoService autoService;

  @Autowired
  private RouteService routeService;

  // Post

  @PostMapping("/addNewJournalByParams")
  public ResponseEntity addNewAutoByParams(@RequestParam String time_in, @RequestParam String time_out, @RequestParam Integer route_id, @RequestParam Integer auto_id) throws ParseException
  {
    try
    {
      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      Date parsedDate_1 = dateFormat.parse(time_in);
      Date parsedDate_2 = dateFormat.parse(time_out);
      Timestamp timestamp_1 = new java.sql.Timestamp(parsedDate_1.getTime());
      Timestamp timestamp_2 = new java.sql.Timestamp(parsedDate_2.getTime());
      return ResponseEntity.ok(journalService.addNewJournal(timestamp_1, timestamp_2, route_id, auto_id));
    } catch (Exception e)
    {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @PostMapping("/addNewJournalByBody")
  public ResponseEntity addNewAutoByBody(@RequestBody JournalEntity journal) throws ParseException, RouteAlreadyExist
  {
    RouteEntity route = null;
    AutoPersonnelEntity autoPersonnel = null;
    AutoEntity auto = null;
    try
    {
      route = routeService.addNewRoute(journal.getRouteId());
      autoPersonnel = autoPersonnelService.addNewAutoPersonnel(journal.getAutoId().getPersonnelId());
      auto = autoService.addNewAuto(journal.getAutoId().getNum(), journal.getAutoId().getColor(), journal.getAutoId().getMark(), journal.getAutoId().getPersonnelId().getId());
      return ResponseEntity.ok(journalService.addNewJournal(journal.getTimeIn(), journal.getTimeOut(), route.getId(), auto.getId()));
    } catch (Exception e)
    {
      if (route != null)
      {
        routeService.deleteRoute(route.getId());
      }
      if (auto != null)
      {
        autoService.deleteById(auto.getId());
      }
      if (autoPersonnel != null)
      {
        autoPersonnelService.deleteById(autoPersonnel.getId());
      }
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping("/get_all_journals")
  public ResponseEntity getAllJournals()
  {
    return ResponseEntity.ok(journalService.getAllJournals());
  }

  @DeleteMapping
  public ResponseEntity deleteById(@RequestParam Integer id)
  {
    return ResponseEntity.ok(journalService.deleteById(id));
  }
}
