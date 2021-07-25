/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidevMobile.forms;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.pidevMobile.models.Product;
import com.esprit.pidevMobile.services.ServiceProduct;
import java.util.ArrayList;
import java.util.List;
import com.codename1.messaging.Message;
import com.codename1.ui.Display;
/**
 *
 * @author Dorsaf
 */
public class ViewOneMine extends Form{
    
    
     private Resources theme;
     int id;
    Form current5;
    Product p;
    public ViewOneMine(Form previous, Resources theme, int id) {
        this.theme=theme;
        this.id = id;
        current5=this;
        setTitle("");
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, evt -> new HomeForm(theme).showBack());
        List<Product> list = new ArrayList<Product>();
        
        list = new ServiceProduct().getAllProducts();
        this.p= new Product();
        
         for (int i = 0; i < list.size(); i++) {
             
             
         
         p = list.get(i);
         if(p.getId() == id)   { 
        Container cnt = new Container(BoxLayout.y());
        Container cnt1 = new Container(BoxLayout.y());
        Container cnt3 = new Container(BoxLayout.y());
        //img
        int j=i+20000;
        String imgname = "three"+j+".png";
        String url = p.getPhoto();
        EncodedImage enc = EncodedImage.createFromImage(this.theme.getImage("load.png"), false);
        Image image = URLImage.createToStorage(enc, imgname, url);
        
        ImageViewer imgV = new ImageViewer(image);
        Label lb1 = new Label(p.getName());
        SpanLabel spl = new SpanLabel(p.getDescription());
        Label lb2 = new Label(String.valueOf(p.getPrice()));
         Button delete = new Button("Delete");
        Button update = new Button("Update");
        
        int k= 10;
       if(i == 0 || i==1 || i == 2)
       {
           k=k+10;
           Label lb3 = new Label("ON PROMOTION!  "+k+"%!! ");
            cnt1.add(lb3);
            
       }
        
        
       
        
        delete.addActionListener(new ActionListener() {
            @Override
             public void actionPerformed(ActionEvent evt) {
                 if( Dialog.show("Confirm", "are you sure you want to delete this?", "OK", "cancel" ))
                 {
                    confirm(id);
                    Message s = new Message("you deleted the item with the id "+p.getId());
          
                    Display.getInstance().sendMessage(new String[] {"dorsaf.tlili@esprit.tn"}, "Product removed", s);
                    new ListMyProductsForm(current5, theme).show();
                 } }
         }
        );
        update.addActionListener((ActionEvent e) -> new UpdateProductForm(current5, theme, p).show());
        
        cnt1.add(lb1);
        cnt1.add(spl);
        cnt1.add(lb2);
        
        
        cnt.add(imgV);
        cnt.add(cnt1);
       
        
      
        
        cnt.setLeadComponent(lb1);
        
        this.add(cnt);
        this.add(delete);
        this.add(update);  
            
        }
         }
        
         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK , e ->previous.showBack());
         
         
         
    }
    
    public void confirm(int i)
    {
        boolean deleteProduct;
                     deleteProduct = new ServiceProduct().deleteProduct(i); 
    }
    
}

