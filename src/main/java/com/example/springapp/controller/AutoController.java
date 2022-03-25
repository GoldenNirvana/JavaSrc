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
public class AutoController {

    @Autowired
    private AutoService autoService;

    @PostMapping
    public ResponseEntity addNewAuto(@RequestBody AutoEntity auto) {
        try {
            return ResponseEntity.ok(autoService.addNewAuto(auto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("1");
        }
    }
}
