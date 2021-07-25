/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidevMobile.models;

import java.util.Date;


/**
 *
 * @author elhak
 */
public class Annonce {
    
    public static final String ACCOUNT_SID = "AC72ef83acbc189db0572bdc48cef7c078";
    public static final String AUTH_TOKEN = "ed53c3b5d5094569b4b2564847d97f0b";
    
    
    private int id;
    private int user_id;
    private String nomAnnonce;
    private String descriptionAnnonce;
    private Date dateAnnonce;

    public Annonce(String nomAnnonce, String descriptionAnnonce, Date dateAnnonce) {
        this.nomAnnonce = nomAnnonce;
        this.descriptionAnnonce = descriptionAnnonce;
        this.dateAnnonce = dateAnnonce;
    }

    public Annonce(int id , String nomAnnonce, String descriptionAnnonce, Date dateAnnonce ) {
        this.id = id;
        this.nomAnnonce = nomAnnonce;
        this.descriptionAnnonce = descriptionAnnonce;
        this.dateAnnonce = dateAnnonce;
        
        
    }

    @Override
    public String toString() {
        return "Annonce{" + "id=" + id + ", nomAnnonce=" + nomAnnonce + ", descriptionAnnonce=" + descriptionAnnonce + ", dateAnnonce=" + dateAnnonce + '}';
    }
 
    public Annonce(String nomAnnonce, String descriptionAnnonce) {
        this.nomAnnonce = nomAnnonce;
        this.descriptionAnnonce = descriptionAnnonce;
    }

    public Annonce(int id, String nomAnnonce, String descriptionAnnonce) {
        this.id = id;
        this.nomAnnonce = nomAnnonce;
        this.descriptionAnnonce = descriptionAnnonce;
    }
   
    public Annonce(int id, int user_id, String nomAnnonce, String descriptionAnnonce, Date dateAnnonce) {
        this.id = id;
        this.user_id = user_id;
        this.nomAnnonce = nomAnnonce;
        this.descriptionAnnonce = descriptionAnnonce;
        this.dateAnnonce = dateAnnonce;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getNomAnnonce() {
        return nomAnnonce;
    }

    public void setNomAnnonce(String nomAnnonce) {
        this.nomAnnonce = nomAnnonce;
    }

    public String getDescriptionAnnonce() {
        return descriptionAnnonce;
    }

    public void setDescriptionAnnonce(String descriptionAnnonce) {
        this.descriptionAnnonce = descriptionAnnonce;
    }

    public Date getDateAnnonce() {
        return dateAnnonce;
    }

    public void setDateAnnonce(Date dateAnnonce) {
        this.dateAnnonce = dateAnnonce;
    }
    
    
}
