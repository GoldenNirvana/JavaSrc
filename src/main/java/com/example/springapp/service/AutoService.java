package com.example.springapp.service;

import com.example.springapp.entity.AutoEntity;
import com.example.springapp.repository.AutoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutoService {

    @Autowired
    private AutoRepo autoRepo;

    public AutoEntity addNewAuto(AutoEntity auto) {
        return autoRepo.save(auto);
    }
}
