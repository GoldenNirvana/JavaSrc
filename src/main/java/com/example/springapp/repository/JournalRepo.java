package com.example.springapp.repository;

import com.example.springapp.entity.AutoEntity;
import com.example.springapp.entity.JournalEntity;
import com.example.springapp.entity.RouteEntity;
import com.example.springapp.model.Route;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface JournalRepo extends CrudRepository<JournalEntity, Integer>
{
  List<JournalEntity> findJournalEntityByAutoId(AutoEntity autoId);

//  Optional<JournalEntity> findByAutoIdAndRouteIdAndTimeInAndTimeOut(Timestamp timeIn, Timestamp timeOut, AutoEntity auto, RouteEntity route);

//   Optional<JournalEntity> findJournalEntityByTimeOutAndTimeInAndRouteIdAndAutoId(Timestamp timeIn, Timestamp timeOut, RouteEntity route, AutoEntity auto);

//  Optional<JournalEntity> findJournalEntityByTimeInAndTimeOut(Timestamp timeIn, Timestamp timeOut);

  List<JournalEntity> findAllByRouteId(RouteEntity route);
}
