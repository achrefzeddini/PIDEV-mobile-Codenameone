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
public class Commentaire {
    private int id;
    private int annonce_id;
    private int user_id;
    private String champCommentaire;
    private Date dateCommentaire;
    
    
    

    //ctor ajout
    public Commentaire(int annonce_id, String champCommentaire) {
        this.annonce_id = annonce_id;
        this.champCommentaire = champCommentaire;
    }
    //ctor affichage
    public Commentaire(int id, String champCommentaire, Date dateCommentaire,int iduser) {
        this.id = id;
        this.champCommentaire = champCommentaire;
        this.dateCommentaire = dateCommentaire;
        this.user_id = iduser;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAnnonce_id() {
        return annonce_id;
    }

    public void setAnnonce_id(int annonce_id) {
        this.annonce_id = annonce_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getChampCommentaire() {
        return champCommentaire;
    }

    public void setChampCommentaire(String champCommentaire) {
        this.champCommentaire = champCommentaire;
    }

    public Date getDateCommentaire() {
        return dateCommentaire;
    }

    public void setDateCommentaire(Date dateCommentaire) {
        this.dateCommentaire = dateCommentaire;
    }
    
    
    
    
}
