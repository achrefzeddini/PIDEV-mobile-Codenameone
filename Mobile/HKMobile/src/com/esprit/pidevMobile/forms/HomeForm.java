/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidevMobile.forms;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.pidevMobile.utils.CurrentUser;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 *
 * @author Testouri Mohamed
 */
public class HomeForm extends Form {
    
    CurrentUser CU = new CurrentUser();
    private Form f;

    public HomeForm(Resources res) {
        super("Home", BoxLayout.y());
        ActionListener<ActionEvent> profile = new ActionListener<ActionEvent>() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ProfileForm(res).show();
            }
        };
        ActionListener<ActionEvent> groups = new ActionListener<ActionEvent>() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new GroupsForm(res).show();
            }
        };
        ActionListener<ActionEvent> mygroups = new ActionListener<ActionEvent>() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new MyGroupsForm(res).show();
            }
        };
        ActionListener<ActionEvent> animals = new ActionListener<ActionEvent>() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    new FormAnimal(f).show();
                } catch (URISyntaxException ex) {
                    //   Logger.getLogger(HomeForm.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    //  Logger.getLogger(HomeForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        ActionListener<ActionEvent> season = new ActionListener<ActionEvent>() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    new FormSeason(f).show();
                } catch (URISyntaxException ex) {
                    //   Logger.getLogger(HomeForm.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    //  Logger.getLogger(HomeForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        ActionListener<ActionEvent> commande = new ActionListener<ActionEvent>() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new FormCommande(f).show();                
            }
        };
        ActionListener<ActionEvent> annonce = new ActionListener<ActionEvent>() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new AnnonceForm().show();                
            }
        };
        ActionListener<ActionEvent> events = new ActionListener<ActionEvent>() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new EventMenuForm().show();                
            }
        };
        ActionListener<ActionEvent> product = new ActionListener<ActionEvent>() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new ProductForm(res).show();                
            }
        };
        getToolbar().addCommandToOverflowMenu("Logout", null, (ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new CurrentUser().disConnect();
                System.out.println(new CurrentUser().getIdCurrentUser());
                new LoginForm(res).showBack();
            }
        });
        getToolbar().addCommandToSideMenu("", null, (ActionListener) (ActionEvent evt) -> {
        });
        getToolbar().addCommandToSideMenu("Profile", null, profile);
        getToolbar().addCommandToSideMenu("My Groups", null, mygroups);
        getToolbar().addCommandToSideMenu("Groups", null, groups);
        getToolbar().addCommandToLeftSideMenu("Annonce", null, annonce);
        getToolbar().addCommandToSideMenu("Animals", null, animals);
        getToolbar().addCommandToSideMenu("Seasons", null, season);
        getToolbar().addCommandToSideMenu("Products", null, product);
        getToolbar().addCommandToSideMenu("Commande", null, commande);
        getToolbar().addCommandToSideMenu("Events", null, events);
        
        Button groupsBTN = new Button("Groups");
        Button mygroupsBTN = new Button("My Groups");
        Button profileBTN = new Button("Profile");
        Button animalsBTN = new Button("Animals");
        Button seasonsBTN = new Button("Seasons");
        Button commandeBTN = new Button("Commande");
        Button annonceBTN = new Button("Annonce");
        Button productBTN = new Button("Product");
        Button eventsBTN = new Button("Events");
        
        groupsBTN.addActionListener(groups);
        mygroupsBTN.addActionListener(mygroups);
        profileBTN.addActionListener(profile);
        animalsBTN.addActionListener(animals);
        seasonsBTN.addActionListener(season);
        commandeBTN.addActionListener(commande);
        annonceBTN.addActionListener(annonce);
        productBTN.addActionListener(product);
        eventsBTN.addActionListener(events);
        
        this.addAll(groupsBTN, mygroupsBTN, animalsBTN, seasonsBTN, productBTN,commandeBTN,  annonceBTN,eventsBTN, profileBTN);
        System.out.println(CU.getIdCurrentUser());
        
    }
}
