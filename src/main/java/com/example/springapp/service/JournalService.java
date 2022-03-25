package com.example.springapp.service;

import com.example.springapp.entity.JournalEntity;
import com.example.springapp.repository.JournalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JournalService {

    @Autowired
    private JournalRepo journalRepo;

    public JournalEntity addNewJournal(JournalEntity journal) {
        return journalRepo.save(journal);
    }
}
