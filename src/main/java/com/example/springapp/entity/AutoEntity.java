package com.example.springapp.entity;

import javax.persistence.*;

@Entity
@Table(name = "auto")
public class AutoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "num", nullable = false, length = 50)
    private String num;

    @Column(name = "Color", nullable = false, length = 50)
    private String color;

    @Column(name = "Mark", nullable = false, length = 50)
    private String mark;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "personnel_id")
    private RouteEntity personnel_id;

    public AutoEntity() {
    }

    public AutoEntity(String num, String color, String mark, RouteEntity personnel_id) {
        this.num = num;
        this.color = color;
        this.mark = mark;
        this.personnel_id = personnel_id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public RouteEntity getPersonnel_id() {
        return personnel_id;
    }

    public void setPersonnel_id(RouteEntity personnel_id) {
        this.personnel_id = personnel_id;
    }
}
