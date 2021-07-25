/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidevMobile.models;

/**
 *
 * @author hp
 */
public class Animal {
    private int id;
    private int idA;
    private String race;
    private String saison;
    private String place;
    private String image;
    private int hunted;

    public Animal(int id, int idA, String race, String saison, String place, String image, int hunted) {
        this.id = id;
        this.idA = idA;
        this.race = race;
        this.saison = saison;
        this.place = place;
        this.image = image;
        this.hunted = hunted;
 
    }

    public Animal(int idA, String race, String saison, String place, String image, int hunted) {
        this.idA = idA;
        this.race = race;
        this.saison = saison;
        this.place = place;
        this.image = image;
        this.hunted = hunted;

    }

//    public Animal(int id, int idA, String race, String saison, String place, String image, int hunted) {
//        this.id = id;
//        this.idA = idA;
//        this.race = race;
//        this.saison = saison;
//        this.place = place;
//        this.image = image;
//        this.hunted = hunted;
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdA() {
        return idA;
    }

    public void setIdA(int idA) {
        this.idA = idA;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getSaison() {
        return saison;
    }

    public void setSaison(String saison) {
        this.saison = saison;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getHunted() {
        return hunted;
    }

    public void setHunted(int hunted) {
        this.hunted = hunted;
    }

//    public int getSeason_id() {
//        return season_id;
//    }
//
//    public void setSeason_id(int season_id) {
//        this.season_id = season_id;
//    }

    @Override
    public String toString() {
        return "Animal{" + "id=" + id + ", idA=" + idA + ", race=" + race + ", saison=" + saison + ", place=" + place + ", image=" + image + ", hunted=" + hunted + '}';
    }

    
    
}
