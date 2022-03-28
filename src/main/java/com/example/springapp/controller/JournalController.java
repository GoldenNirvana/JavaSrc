package com.example.springapp.controller;

import com.example.springapp.service.JournalService;
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
  private JournalService journalService;

  @PostMapping("/addNewJournal")
  public ResponseEntity addNewAutoByParams(@RequestParam String time_in, @RequestParam String time_out, @RequestParam Integer route_id, @RequestParam Integer auto_id) throws ParseException
  {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
    Date parsedDate_1 = dateFormat.parse(time_in);
    Date parsedDate_2 = dateFormat.parse(time_out);
    Timestamp timestamp_1 = new java.sql.Timestamp(parsedDate_1.getTime());
    Timestamp timestamp_2 = new java.sql.Timestamp(parsedDate_2.getTime());
    return ResponseEntity.ok(journalService.addNewJournal(timestamp_1, timestamp_2, route_id, auto_id));
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
