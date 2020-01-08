package com.example.magazin.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String names;
    private String material;
    private String color;
    private String description;
    private Integer cost;

    public Message(){

    }
    public Message(String names, String description, String material, String color, int cost) {
        this.names=names;
        this.description=description;
        this.material=material;
        this.color=color;
        this.cost=cost;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMaterial() {return material; }

    public void setMaterial(String material) { this.material = material;}

    public String getColor() {return color;}

    public void setColor(String color) {this.color = color; }

    public Integer getCost() {return cost; }

    public void setCost(Integer cost) { this.cost = cost; }
}

