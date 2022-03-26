package com.example.springapp.service;

import com.example.springapp.entity.RouteEntity;
import com.example.springapp.exception.RouteAlreadyExist;
import com.example.springapp.exception.RouteNotFound;
import com.example.springapp.model.Route;
import com.example.springapp.repository.RouteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RouteService
{
  @Autowired
  private RouteRepo routeRepo;

  public RouteEntity addNewRoute(RouteEntity route) throws RouteAlreadyExist
  {
    if (routeRepo.findByName(route.getName()) != null)
    {
      throw new RouteAlreadyExist("Маршрут с таким именем уже существует");
    }
    return routeRepo.save(route);
  }

  public Route getOne(Integer id) throws RouteNotFound
  {
    RouteEntity route = routeRepo.findById(id).get();
    if (route == null)
    {
      throw new RouteNotFound("Route wasn't found.");
    }
    return Route.toModel(route);
  }

  public Integer deleteRoute(Integer id)
  {
    routeRepo.deleteById(id);
    return id;
  }

  public List<RouteEntity> getAllRoutes()
  {
    return (List<RouteEntity>) routeRepo.findAll();
  }
}
