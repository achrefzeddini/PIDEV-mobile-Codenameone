/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidevMobile.models;

import java.util.Date;




/**
 *
 * @author hp
 */
public class Season {
    
    private int id;
    private String nom;
    private Date start;
    private Date finish;
    private String description;

    public Season(int id, String nom, Date start, Date finish, String description) {
        this.id = id;
        this.nom = nom;
        this.start = start;
        this.finish = finish;
        this.description = description;
    }

    public Season(String nom, Date start, Date finish, String description) {
        this.nom = nom;
        this.start = start;
        this.finish = finish;
        this.description = description;
    }

    public Season(int id, String nom, String description) {
        this.id = id;
        this.nom = nom;
        this.description = description;
    }

    public Season() {
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getFinish() {
        return finish;
    }

    public void setFinish(Date finish) {
        this.finish = finish;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Season{" + "id=" + id + ", nom=" + nom + ", start=" + start + ", finish=" + finish + ", description=" + description + '}';
    }
    
    
    
    
}
