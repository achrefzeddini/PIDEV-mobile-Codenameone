/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidevMobile.forms;


import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Dorsaf
 */
public class AnotherOne extends Form{
    
    
    
    private Resources theme;
    Form current8 = new Form(BoxLayout.y());
    
    public AnotherOne(Form previous, Resources theme) {
       this.theme=theme; 
       current8=this;
       getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, evt -> new HomeForm(theme).showBack());
        
        
        setTitle("IDKKK");
        
        
    }


    
}
