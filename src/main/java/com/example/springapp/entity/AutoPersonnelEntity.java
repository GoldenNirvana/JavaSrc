package com.example.springapp.entity;

import javax.persistence.*;

@Entity
@Table(name = "auto_personnel")
public class AutoPersonnelEntity
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  private Integer id;

  @Column(name = "first_name", nullable = false, length = 50)
  private String first_name;

  @Column(name = "last_name", nullable = false, length = 50)
  private String last_name;

  @Column(name = "pather_name", nullable = false, length = 50)
  private String pather_name;

  public AutoPersonnelEntity()
  {
  }

  public AutoPersonnelEntity(String first_name, String last_name, String pather_name)
  {
    this.first_name = first_name;
    this.last_name = last_name;
    this.pather_name = pather_name;
  }

  public Integer getId()
  {
    return id;
  }

  public void setId(Integer id)
  {
    this.id = id;
  }

  public String getFirst_name()
  {
    return first_name;
  }

  public void setFirst_name(String first_name)
  {
    this.first_name = first_name;
  }

  public String getLast_name()
  {
    return last_name;
  }

  public void setLast_name(String last_name)
  {
    this.last_name = last_name;
  }

  public String getPather_name()
  {
    return pather_name;
  }

  public void setPather_name(String pather_name)
  {
    this.pather_name = pather_name;
  }
}
