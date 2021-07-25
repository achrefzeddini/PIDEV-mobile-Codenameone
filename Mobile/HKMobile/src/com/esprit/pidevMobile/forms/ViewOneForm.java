/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidevMobile.forms;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.pidevMobile.models.Product;
import com.esprit.pidevMobile.services.ServiceProduct;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dorsaf
 */
public class ViewOneForm extends Form{
    
    
     private Resources theme;
     int id;
    
    public ViewOneForm(Form previous, Resources theme, int id) {
        this.theme=theme;
        this.id = id;
        setTitle("");
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, evt -> new HomeForm(theme).showBack());
        List<Product> list = new ArrayList<Product>();
        
        list = new ServiceProduct().getAllProducts();
        
        
         for (int i = 0; i < list.size(); i++) {
             
             
         Product p= new Product();
         p = list.get(i);
         if(p.getId() == id)   { 
        Container cnt = new Container(BoxLayout.y());
        Container cnt1 = new Container(BoxLayout.y());
        //img
        int j=i+1000;
        String imgname = "three"+j+".png";
        String url = p.getPhoto();
        EncodedImage enc = EncodedImage.createFromImage(this.theme.getImage("load.png"), false);
        Image image = URLImage.createToStorage(enc, imgname, url);
        
        ImageViewer imgV = new ImageViewer(image);
        Label lb1 = new Label(p.getName());
        SpanLabel spl = new SpanLabel(p.getDescription());
        Label lb2 = new Label(String.valueOf(p.getPrice()));
        
        int k= 10;
       if(i == 0 || i==1 || i == 2)
       {
           k=k+10;
           Label lb3 = new Label("ON PROMOTION!  "+k+"%!! ");
            cnt1.add(lb3);
            
       }
        
        Button buy = new Button("Buy Now");
       // Label lb5 = new Label("BUY THIS PRODUCT");
       
        
       
        cnt1.add(lb1);
        cnt1.add(spl);
        
        cnt1.add(lb2);
        
       
        
        cnt.add(imgV);
        cnt.add(cnt1);
        
      
        
        buy.addPointerReleasedListener((evt) -> {
            Dialog.show("Information", "are you sure you want to buy", "OK", null);
        });
      
        
        cnt.setLeadComponent(lb1);
        
        this.add(cnt);
         this.add(buy);  
         
         
            
        }
         }
        
         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK , e ->previous.showBack());
         
         
         
    }
    
    
}
