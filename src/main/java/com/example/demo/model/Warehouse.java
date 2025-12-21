package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String location;

    public Warehouse() {}

    public Long getId() { return id; }
    public String getLocation() { return location; }

    public void setId(Long id) { this.id = id; }
    public void setLocation(String location) { this.location = location; }
}
