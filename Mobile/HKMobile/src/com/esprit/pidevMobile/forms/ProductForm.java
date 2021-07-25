/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidevMobile.forms;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Form;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Dorsaf
 */
public class ProductForm extends Form {
    
    Form current;
    private Resources theme;
    
    public ProductForm(Resources theme){
        current=this;
         this.theme = theme;
        setTitle("Home");
        setLayout(BoxLayout.y());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, evt -> new HomeForm(theme).showBack());
        
        Label wel = new Label("welcome to our shop!");
        this.add(wel);
        
        this.add(new SpanLabel("In addition to all the other services, this platform gives you the ability to explore a shop full of products added by users on this application, you may also add and handle your own products!"));
        
        
        add(new Label("choose an option:"));
        Button btnAddProduct = new Button("Add a product");
        Button btnListProducts = new Button("List all Products");
        Button btnListMyProducts = new Button("List my products");
        Button btnchart = new Button("view charts");
       
        
       
        btnAddProduct.addActionListener(e -> new AddProductForm(current, theme).show());
        btnListProducts.addActionListener(e -> new ListProductsForm(current, theme).show());
        btnListMyProducts.addActionListener(e -> new ListMyProductsForm(current, theme).show());
        btnchart.addActionListener(e -> new ChartForm(current, theme).show());
       
        
        addAll(btnAddProduct, btnListProducts, btnListMyProducts, btnchart);
        /*$("Label").setBgColor(0xcccccc)
          .setBgTransparency(255);*/
        
        
    }
    
}
