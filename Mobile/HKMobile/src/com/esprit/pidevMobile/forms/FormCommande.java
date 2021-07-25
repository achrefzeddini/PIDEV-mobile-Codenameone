/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidevMobile.forms;

import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.esprit.pidevMobile.models.Commande;
import com.esprit.pidevMobile.services.CommandeService;
import java.util.ArrayList;


/**
 *
 * @author hp
 */
public class FormCommande extends Form{
    Form f;
    private Resources theme;
    public FormCommande(Form previous) {
        super("Orders", BoxLayout.y());
        CommandeService as = new CommandeService();
        ArrayList<Commande> list = as.getAllCommandes();
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, evt -> new HomeForm(theme).showBack());
        for (Commande an : list) {
         
         Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
            Label produit = new Label("Produit : " +(an.getProduit()));
            SpanLabel date = new SpanLabel("date : " +an.getDate());
            SpanLabel price = new SpanLabel("Price : "+ String.valueOf(an.getPrice()));
           Button btnorder = new Button("Order");
           Button btnSupprimer = new Button("Delete");
//            produit.addPointerReleasedListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent evt) {
//               Form updatecommande = new Form(BoxLayout.y());
//                Label produit = new Label("Produit : " +(an.getProduit()));
//            SpanLabel date = new SpanLabel("date : " +an.getDate());
//            SpanLabel price = new SpanLabel("Price : "+ String.valueOf(an.getPrice()));
//               updatecommande.add(produit);
//               updatecommande.add(date);
//               updatecommande.add(price);
//               updatecommande.show();
//                
//            }
//        });
           btnSupprimer.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                 if(Dialog.show("Confirmation", "Are you Sure you Want to Delete "+ an.getProduit() , "Yes", "Cancel")){
                        CommandeService as = new CommandeService();
                        as.supprimerCommande(an.getId());
                        System.out.println("delete avec succes ");
                        ToastBar.Status status = ToastBar.getInstance().createStatus();
            status.setMessage("Deleting : "+ an.getProduit());
            status.setShowProgressIndicator(true);
            status.setIcon(createIcon(FontImage.MATERIAL_DELETE));
            status.show();
            // Now do something that takes a while
            Display.getInstance().invokeAndBlock(()->{
                Util.sleep(2000);

            });
                 }    
                           new FormCommande(f).show();
                                    }
                });
           btnorder.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                         if(Dialog.show("Confirmation", "Are you Sure you Want to Order "+ an.getProduit() , "Yes", "Cancel")){
                       CommandeService as = new CommandeService();
                        as.updateCommande(an.getId());
                         sendMail("achref.zeddini@esprit.tn",an.getProduit());
                       
//                        Dialog.show("", "Product Ordered", "OK", null);    
                        ToastBar.showMessage( " "+ an.getProduit() +" Ordered ", FontImage.MATERIAL_ADD_SHOPPING_CART);
                        System.out.println("modifié");
                    
                        new FormCommande(f).show();
                        
                        }
                    }
                });

           
            C1.add(produit);
            C1.add(date);
            C1.add(price);
            C1.add(btnorder);
            C1.add(btnSupprimer); 
            this.add(C1);
    }
    this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
             new HomeForm(theme).show();
        });
}
    private Image createIcon(char charcode) {
        int iconWidth = Display.getInstance().convertToPixels(8, true);
        Style iconStyle = new Style();
        Font iconFont = Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE);
        if (Font.isNativeFontSchemeSupported()) {
            iconFont = Font.createTrueTypeFont("native:MainBold", null).derive((int)(iconWidth * 0.5), Font.STYLE_BOLD);
        }
        iconStyle.setFont(iconFont);
        iconStyle.setFgColor(0xffffff);
        iconStyle.setBgTransparency(0);

        FontImage completeIcon = FontImage.createMaterial(charcode, iconStyle);
        return completeIcon;
    }
     public void sendMail(String Email, String order) {
        ConnectionRequest req = new ConnectionRequest();
        req.setUrl("http://localhost/journal/sendmail.php?email="+ Email+"&order="+order);

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
}