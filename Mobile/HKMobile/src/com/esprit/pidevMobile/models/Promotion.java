/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidevMobile.models;

/**
 *
 * @author Dorsaf
 */
public class Promotion {
    
    
    private int id;
    private String name;
    private int percentage;
    private String description;
    private int nbdays;

    public Promotion(int id, String name, int percentage, String description, int nbdays) {
        this.id = id;
        this.name = name;
        this.percentage = percentage;
        this.description = description;
        this.nbdays = nbdays;
    }

    public Promotion(String name, int percentage, String description, int nbdays) {
        this.name = name;
        this.percentage = percentage;
        this.description = description;
        this.nbdays = nbdays;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNbdays() {
        return nbdays;
    }

    public void setNbdays(int nbdays) {
        this.nbdays = nbdays;
    }

    @Override
    public String toString() {
        return "Promotion{" + "id=" + id + ", name=" + name + ", percentage=" + percentage + ", description=" + description + ", nbdays=" + nbdays + '}';
    }
    
    
}
