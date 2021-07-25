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
public class Commande {
    
    private int id;
    private String produit;
    private Date date;
    private double price;
    private int state;
    private int user_id;
    private Users user;

    public Commande(int id, String produit, Date date, double price, int state, int user_id) {
        this.id = id;
        this.produit = produit;
        this.date = date;
        this.price = price;
        this.state = state;
        this.user_id = user_id;
    }

    public Commande(int id, String produit, Date date, double price, int state) {
        this.id = id;
        this.produit = produit;
        this.date = date;
        this.price = price;
        this.state = state;
    }

    public Commande() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduit() {
        return produit;
    }

    public void setProduit(String produit) {
        this.produit = produit;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", produit=" + produit + ", date=" + date + ", price=" + price + ", state=" + state + ", user_id=" + user_id + '}';
    }
      
    
    
    
}
