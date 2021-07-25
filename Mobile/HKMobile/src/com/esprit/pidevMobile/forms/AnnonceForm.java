/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidevMobile.forms;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author elhak
 */
public class AnnonceForm extends Form {
    Resources res;
    public AnnonceForm() {
        super("Annonce", BoxLayout.y());
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, evt -> new HomeForm(res).showBack());
        
        Button btnAddAnnonce = new Button("Add Annonce");
        Button btnAnnoncesList = new Button("Annonces List");
        Button btnMyAnnoncesList = new Button("My Annonces List");
        
        btnAddAnnonce.addActionListener((evt) -> {
            new AddAnnonceForm(this).show();
        });
        btnAnnoncesList.addActionListener((evt) -> {
            new AnnoncesListForm(this).show();
        });
        btnMyAnnoncesList.addActionListener((evt) -> {
            new MyAnnoncesListForm(this).show();
        });
        
        this.addAll(new Label("Choose an option :"), btnAddAnnonce, btnAnnoncesList ,btnMyAnnoncesList );
    }
}


