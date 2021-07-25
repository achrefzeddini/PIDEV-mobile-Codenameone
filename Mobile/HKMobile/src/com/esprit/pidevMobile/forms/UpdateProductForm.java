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
public class UpdateProductForm extends Form{
    private Resources theme;
    Form current6;
    public UpdateProductForm(Form previous, Resources theme, Product p) {
        current6=this;
        this.theme=theme;
        setTitle("Update a product");
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, evt -> new HomeForm(theme).showBack());
        TextField name = new TextField(p.getName(), "name");
        TextField price = new TextField(String.valueOf(p.getPrice()), "price");
        TextField description = new TextField(p.getDescription(), "description");
        TextField photo = new TextField(p.getPhoto(), "photo");
        
        Button btn = new Button("update");
        
        btn.addActionListener((evt) -> {
            if ((name.getText().length() == 0) || (price.getText().length() == 0)|| (description.getText().length() == 0)|| (photo.getText().length() == 0)) {
                Dialog.show("Alert", "Please fill all the fields", "OK", null);
            } else {
                try {
                    Product p2 = new Product(p.getId(), name.getText(), Float.parseFloat(price.getText()), description.getText(), photo.getText(), p.getId_user());
                    if (new ServiceProduct().updateProduct(p2)) {
                        if( Dialog.show("Confirm", "Product updated", "OK", null ))
                 {
                    
                    new ListMyProductsForm(current6, theme).show();
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
