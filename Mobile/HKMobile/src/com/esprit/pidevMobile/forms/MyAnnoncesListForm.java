/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidevMobile.forms;

import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.pidevMobile.models.Annonce;
import com.esprit.pidevMobile.services.ServiceAnnonce;
import java.util.ArrayList;


import com.codename1.components.ToastBar; //notif
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.util.Resources;


/**
 *
 * @author elhak
 */
public class MyAnnoncesListForm extends Form {
    
    public void sendMail(String Email) {
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl("http://localhost/journal/sendmail.php?email="+ Email);

        req.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {

                byte[] data = (byte[]) evt.getMetaData();
                String s = new String(data);
                System.err.println("Mail Sent");
            }
        });

        NetworkManager.getInstance().addToQueue(req);
    }
    
    Resources res;
    public MyAnnoncesListForm(Form previous) {
        super("Annonces list", BoxLayout.y());
getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, evt -> new HomeForm(res).showBack());
        ServiceAnnonce as = new ServiceAnnonce();
        ArrayList<Annonce> list = as.getMyAnnonces();
        
        for (Annonce an : list) {           
            
            Button delete = new Button("Delete");
            Button update = new Button("Update");
            MultiButton fourLinesIcon = new MultiButton("- " +(an.getNomAnnonce()));
            fourLinesIcon.setTextLine1("" +an.getId());
            fourLinesIcon.setTextLine2("" +an.getDescriptionAnnonce());
            fourLinesIcon.setTextLine3("Announce date : "+(an.getDateAnnonce())); 
            
            update.addActionListener(new ActionListener() {
                @Override
                    public void actionPerformed(ActionEvent evt) {

                        Form UpdateAnnonceForm = new Form(BoxLayout.y());
                        TextField tfnomAnnonce = new TextField(an.getNomAnnonce());
                        TextField tfdescriptionAnnonce = new TextField(an.getDescriptionAnnonce());
                        Button update = new Button("Update"); 
                        
                        //  MAIL    *   MAIL    *   MAIL    *   MAIL    *   MAIL    *   MAIL   
                      //  sendMail("imen.elhakim@esprit.tn");
                        //  MAIL    *   MAIL    *   MAIL    *   MAIL    *   MAIL    *   MAIL   
                        
                        UpdateAnnonceForm.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,(ev) -> {
                        previous.showBack();
                        });

                        UpdateAnnonceForm.addAll(tfnomAnnonce,tfdescriptionAnnonce,update);
                        UpdateAnnonceForm.show();

                        update.addActionListener(new ActionListener() {
                            @Override
                                public void actionPerformed(ActionEvent evt) {
                                    System.out.println("Announce ID : " + an.getId());

                                    an.setNomAnnonce(tfnomAnnonce.getText());
                                    an.setDescriptionAnnonce(tfdescriptionAnnonce.getText());

                                    as.updateAnnonce(an);
                                    
                                    ToastBar.showMessage( "ANNOUNCE UPDATED ! ", FontImage.MATERIAL_THUMB_UP);//notif    
                                }   
                        });
                }
            });
 
            delete.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent evt) {
                    System.out.println("Announce ID : " + an.getId());
                        as.deleteAnnonce(an.getId());
                        System.out.println("deleted! ");
                        ToastBar.showMessage( "ANNOUNCE DELETED ! ", FontImage.MATERIAL_THUMB_UP);//notif
                        MyAnnoncesListForm MAL = new MyAnnoncesListForm(previous);
                        MAL.show();
                }
            });
           
            this.addAll(fourLinesIcon,update,delete);

            this.getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, (evt) -> {
                previous.showBack();
            });
    }
}
}
