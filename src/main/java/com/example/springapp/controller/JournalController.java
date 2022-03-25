package com.example.springapp.controller;

import com.example.springapp.entity.JournalEntity;
import com.example.springapp.exception.RouteAlreadyExist;
import com.example.springapp.service.JournalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/journals")
public class JournalController {

    @Autowired
    private JournalService journalService;

    @PostMapping
    public ResponseEntity addNewJournal(@RequestBody JournalEntity journal) {
        try {
            return ResponseEntity.ok(journalService.addNewJournal(journal));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("2");
        }
    }
}
