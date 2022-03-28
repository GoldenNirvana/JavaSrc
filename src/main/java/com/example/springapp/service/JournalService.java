package com.example.springapp.service;

import com.example.springapp.entity.AutoEntity;
import com.example.springapp.entity.JournalEntity;
import com.example.springapp.entity.RouteEntity;
import com.example.springapp.repository.AutoRepo;
import com.example.springapp.repository.JournalRepo;
import com.example.springapp.repository.RouteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class JournalService
{

  @Autowired
  private JournalRepo journalRepo;

  @Autowired
  private AutoRepo autoRepo;

  @Autowired
  private RouteRepo routeRepo;

  public JournalEntity addNewJournal(Timestamp time_in, Timestamp time_out, Integer route_id, Integer auto_id)
  {
    Optional<AutoEntity> auto = autoRepo.findById(auto_id);
    Optional<RouteEntity> route = routeRepo.findById(route_id);

    if (auto.isPresent() && route.isPresent())
    {
      JournalEntity journal = new JournalEntity(time_in, time_out, auto.get(), route.get());
      System.out.println("Журнал сохранён");
      return journalRepo.save(journal);
    }
    throw new RuntimeException();
  }

  public List<JournalEntity> getAllJournals()
  {
    return (List<JournalEntity>) journalRepo.findAll();
  }

  public Boolean deleteById(Integer id)
  {
    Optional<JournalEntity> journal = journalRepo.findById(id);
    if (journal.isPresent())
    {
      journalRepo.deleteById(id);
      return true;
    }
    return false;
  }
}
