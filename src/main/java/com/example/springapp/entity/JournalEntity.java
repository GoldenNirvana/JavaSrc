package com.example.springapp.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "journal")
public class JournalEntity
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Integer id;

  @Column(name = "time_out")
  private Timestamp time_out;

  @Column(name = "time_in")
  private Timestamp time_in;

  @ManyToOne(optional = false, cascade = CascadeType.MERGE)
  @JoinColumn(name = "route_id")
  private RouteEntity route_id;

  @ManyToOne(optional = false, cascade = CascadeType.MERGE)
  @JoinColumn(name = "auto_id")
  private AutoEntity auto_id;

  public JournalEntity()
  {
  }

  public JournalEntity(Timestamp time_in, Timestamp time_out, AutoEntity auto_id, RouteEntity route_id)
  {
    this.auto_id = auto_id;
    this.route_id = route_id;
    this.time_out = time_out;
    this.time_in = time_in;
  }

  public Integer getId()
  {
    return id;
  }

  public void setId(Integer id)
  {
    this.id = id;
  }

  public Timestamp getTime_out()
  {
    return time_out;
  }

  public void setTime_out(Timestamp time_out)
  {
    this.time_out = time_out;
  }

  public Timestamp getTime_in()
  {
    return time_in;
  }

  public void setTime_in(Timestamp time_in)
  {
    this.time_in = time_in;
  }

  public RouteEntity getRoute_id()
  {
    return route_id;
  }

  public void setRoute_id(RouteEntity route_id)
  {
    this.route_id = route_id;
  }

  public AutoEntity getAuto_id()
  {
    return auto_id;
  }

  public void setAuto_id(AutoEntity auto_id)
  {
    this.auto_id = auto_id;
  }
}
