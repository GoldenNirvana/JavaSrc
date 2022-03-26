package com.example.springapp.controller;

import com.example.springapp.entity.RouteEntity;
import com.example.springapp.exception.RouteAlreadyExist;
import com.example.springapp.exception.RouteNotFound;
import com.example.springapp.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/routes")
public class RouteController
{

  @Autowired
  private RouteService routeService;

  @PostMapping
  public ResponseEntity addNewRoute(@RequestBody RouteEntity route)
  {
    try
    {
      routeService.addNewRoute(route);
      return ResponseEntity.ok("Маршрут сохранён.");
    } catch (RouteAlreadyExist alreadyExist)
    {
      return ResponseEntity.badRequest().body(alreadyExist.getMessage());
    } catch (Exception e)
    {
      return ResponseEntity.badRequest().body("Error route");
    }
  }

  @GetMapping("get_route_by_id")
  public ResponseEntity getOneRoute(@RequestParam Integer id)
  {
    try
    {
      return ResponseEntity.ok(routeService.getOne(id));
    } catch (RouteNotFound e)
    {
      return ResponseEntity.badRequest().body(e.getMessage());
    } catch (Exception e)
    {
      return ResponseEntity.badRequest().body("Error");
    }
  }

  @GetMapping("get_all_route")
  public ResponseEntity getAllRoutes()
  {
    return ResponseEntity.ok(routeService.getAllRoutes());
  }

  @DeleteMapping("/{id}")
  public ResponseEntity deleteRoute(@PathVariable Integer id)
  {
    try
    {
      return ResponseEntity.ok(routeService.deleteRoute(id));
    } catch (Exception e)
    {
      return ResponseEntity.badRequest().body("Error");
    }
  }
}
