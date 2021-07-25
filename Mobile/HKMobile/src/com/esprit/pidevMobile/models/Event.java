/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidevMobile.models;

/**
 *
 * @author jha
 */
public class Event {
    private int idEvent;
    private int nbrplaces;
    private String localisation;
    private String titre;
    private String image;
    private float hdebut;
    private float hfin;
    private float prix;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    
    
    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public int getNbrplaces() {
        return nbrplaces;
    }

    public void setNbrplaces(int nbrplaces) {
        this.nbrplaces = nbrplaces;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public float getHdebut() {
        return hdebut;
    }

    public void setHdebut(float hdebut) {
        this.hdebut = hdebut;
    }

    public float getHfin() {
        return hfin;
    }

    public void setHfin(float hfin) {
        this.hfin = hfin;
    }
    
    
    
}
