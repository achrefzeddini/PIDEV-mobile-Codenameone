/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidevMobile.forms;

import com.codename1.ui.*;
import com.codename1.ui.util.Resources;
import com.esprit.pidevMobile.models.Product;
import com.esprit.pidevMobile.services.ServiceProduct;


/**
 *
 * @author Dorsaf
 */
public class AddProductForm extends Form{
    private Resources theme;
    
    public AddProductForm(Form previous, Resources theme) {
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, evt -> new HomeForm(theme).showBack());
        this.theme=theme;
        setTitle("Add a new product");
        TextField name = new TextField(null, "name");
        TextField price = new TextField(null, "price");
        TextField description = new TextField(null, "description");
        TextField photo = new TextField(null, "photo");
        
        Button btn = new Button("Add");
        
        btn.addActionListener((evt) -> {
            if ((name.getText().length() == 0) || (price.getText().length() == 0)|| (description.getText().length() == 0)|| (photo.getText().length() == 0)) {
                Dialog.show("Alert", "Please fill all the fields", "OK", null);
            } else {
                try {
                    Product p = new Product(name.getText(), Float.parseFloat(price.getText()), description.getText(), photo.getText());
                    if (new ServiceProduct().addProduct(p)) {
                        if( Dialog.show("Confirm", "Product added", "OK", null ))
                 {
                    
                    new ListMyProductsForm(previous, theme).show();
                 }
                        
                    } else {
                        Dialog.show("ERROR", "Server error", "OK", null);
                    }
                } catch (NumberFormatException e) {
                    Dialog.show("ERROR", "price must be a number", "OK", null);
                }

            }
        });
        
        
        this.addAll(name, price, description, photo, btn);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK , e ->previous.showBack());
        
    }
    
}
