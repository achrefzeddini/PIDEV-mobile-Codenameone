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
public class Product {
    
    private int id;
    private String name;
    private float price;
    private String description;
    private String photo;
    private int id_promotion;
    private int id_user;

    public Product() {
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public Product(int id, String name, float price, String description, String photo, int id_promotion, int id_user) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.photo = photo;
        this.id_promotion = id_promotion;
        this.id_user = id_user;
    }

    
    
    public Product(int id, String name, float price, String description, String photo, int id_promotion) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.photo = photo;
        this.id_promotion = id_promotion;
    }

    public Product(String name, float price, String description, String photo, int id_promotion) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.photo = photo;
        this.id_promotion = id_promotion;
    }

    public Product(String name, float price, String description, String photo) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.photo = photo;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getId_promotion() {
        return id_promotion;
    }

    public void setId_promotion(int id_promotion) {
        this.id_promotion = id_promotion;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", price=" + price + ", description=" + description + ", photo=" + photo + ", id_promotion=" + id_promotion + '}';
    }
    
    
    
    
    
}
