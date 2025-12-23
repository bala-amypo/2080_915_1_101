package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getLocation() { return location; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setLocation(String location) { this.location = location; }
}
