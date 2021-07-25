/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidevMobile.forms;

import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.pidevMobile.utils.CurrentUser;

/**
 *
 * @author Testouri Mohamed
 */
public class ProfileForm extends Form {
    CurrentUser CU = new CurrentUser();
        public ProfileForm(Resources res){
        super("Profile", BoxLayout.y());
        getToolbar().addCommandToOverflowMenu("Edit Profile", null, (ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                //modifier profil
            }
        });
        getToolbar().addCommandToOverflowMenu("Logout", null, (ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new LoginForm(res).showBack();
            }
        });
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, evt -> new HomeForm(res).showBack());
    
               Label firstnameLB = new Label("First Name: "+CU.getCurrentUser().getFirst_name());
        Label lastnameLB = new Label("Last Name: "+CU.getCurrentUser().getLast_name());
        Label phoneLB = new Label("Phone: "+String.valueOf(CU.getCurrentUser().getPhone()));
        Label emailLB = new Label("E-mail: "+CU.getCurrentUser().getEmail());
        
        this.addAll(firstnameLB, lastnameLB, phoneLB, emailLB);
        
        
        
    }
}
