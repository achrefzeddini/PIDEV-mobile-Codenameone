/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidevMobile.forms;

import com.codename1.components.MultiButton;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
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
public class ListProductsForm extends Form{
    
    private Resources theme;
    
    Form current3 = new Form(BoxLayout.y());
    public ListProductsForm(Form previous, Resources theme) {
        this.theme=theme;
        current3=this;
        setTitle("All products");
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, evt -> new HomeForm(theme).showBack());
        
        
        
        List<Product> list = new ArrayList<Product>();
        
        list = new ServiceProduct().getAllProducts();
        
         for (int i = 0; i < list.size(); i++) {
             
             
         
         Product p = list.get(i);
         
        
         String desc =p.getDescription();
         
         MultiButton m = new MultiButton();
            m.setTextLine1(p.getName());
            m.setTextLine2(String.valueOf(p.getPrice()));
            m.setTextLine3(desc);
        int j = i+6000;   
        String imgname = "something"+j+".png";
        String url = p.getPhoto();
        EncodedImage enc = EncodedImage.createFromImage(this.theme.getImage("load.png"), false);
        Image image = URLImage.createToStorage(enc, imgname, url);
         
         int fiveMM = Display.getInstance().convertToPixels(12);
         final Image finalDuke = image.scaledWidth(fiveMM);
            if(image != null) {
                m.setIcon(image.fill(finalDuke.getWidth(), finalDuke.getHeight()));
            } else {
                m.setIcon(finalDuke);
            }
          
            int num = p.getId();
            
        m.addActionListener(
            e -> new ViewOneForm(current3, theme, num).show()
        );
        
            this.add(m); 
       /* Container cnt = new Container(BoxLayout.x());
        Container cnt1 = new Container(BoxLayout.y());
        //img
        String imgname = "something"+i+".png";
        String url = p.getPhoto();
        EncodedImage enc = EncodedImage.createFromImage(this.theme.getImage("load.png"), false);
        Image image = URLImage.createToStorage(enc, imgname, url);
        
        ImageViewer imgV = new ImageViewer(image);
        Label lb1 = new Label(p.getName());
        SpanLabel spl = new SpanLabel(p.getDescription().substring(0,50) + "...");
        cnt1.add(lb1);
        cnt1.add(spl);
        
        cnt.add(imgV);
        cnt.add(cnt1);
        int num = p.getId();
        
             System.out.println(num);
        lb1.addPointerReleasedListener(
            e -> new ViewOneForm(current3, theme, num).show()
        );
        imgV.addPointerReleasedListener(
            e -> new ViewOneForm(current3, theme, num).show()
        );
        
        cnt.setLeadComponent(lb1);
        
        this.add(cnt);*/
            
            
        }
        
         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK , e ->previous.showBack());
         
         
   this.getToolbar().addSearchCommand(e -> {
    String text = (String)e.getSource();
    if(text == null || text.length() == 0) {
        // clear search
        for(Component cmp : this.getContentPane()) {
            cmp.setHidden(false);
            cmp.setVisible(true);
        }
        this.getContentPane().animateLayout(150);
    } else {
        text = text.toLowerCase();
        for(Component cmp : this.getContentPane()) {
            MultiButton mb = (MultiButton)cmp;
            String line1 = mb.getTextLine1();
            String line2 = mb.getTextLine2();
            boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1 ||
                    line2 != null && line2.toLowerCase().indexOf(text) > -1;
            mb.setHidden(!show);
            mb.setVisible(show);
        }
        this.getContentPane().animateLayout(150);
    }
}, 4);
        
    }
    
    
    
    
    
}
