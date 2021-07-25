/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidevMobile.forms;

import com.codename1.components.MultiButton;
import com.codename1.components.ToastBar;
import com.codename1.ui.Form;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.pidevMobile.services.ServiceAnnonce;
import com.esprit.pidevMobile.models.Annonce;
import com.esprit.pidevMobile.models.Commentaire;
import com.esprit.pidevMobile.utils.CurrentUser;
import java.util.ArrayList;

/**
 *
 * @author elhak
 */
public class AnnoncesListForm extends Form {
    Resources res;
    CurrentUser CU = new CurrentUser();
    public AnnoncesListForm(Form previous) {
        super("Annonces list", BoxLayout.y());
      getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, evt -> new HomeForm(res).showBack());
        ServiceAnnonce as = new ServiceAnnonce();
        ArrayList<Annonce> list = as.getAllAnnonces();
        
        for (Annonce an : list) {
         
            MultiButton fourLinesIcon = new MultiButton("- " +(an.getId()));
            fourLinesIcon.setTextLine1("" +an.getNomAnnonce());
            fourLinesIcon.setTextLine2("" +an.getDescriptionAnnonce());
            fourLinesIcon.setTextLine3("Announce date : "+(an.getDateAnnonce()));

            this.add(fourLinesIcon);        

  /*-------------------------------     COMMENTAIRES -----------------------------------------*/          

            fourLinesIcon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                   
                Form AddCommentForm = new Form(BoxLayout.y());
                
                AddCommentForm.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,(ev) -> {
                previous.showBack();
                });
                
                Label lbnomAnnonce = new Label(an.getNomAnnonce());
                Label lbdescriptionAnnonce = new Label(an.getDescriptionAnnonce());
      
                Button comment = new Button("Comment"); 
                Button commentlist = new Button("Comments list");      
                
                AddCommentForm.addAll(lbnomAnnonce,lbdescriptionAnnonce,comment,commentlist);
                AddCommentForm.show();
               
                /*------------------      AJOUT COMMENTAIRE      ------------------*/
                comment.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        Form AddCommentForm = new Form(BoxLayout.y());
                        Label lbnomAnnonce = new Label(an.getNomAnnonce());
                        Label lbdescriptionAnnonce = new Label(an.getDescriptionAnnonce());
                        TextField champCommentaire = new TextField(null, "Express yourself !");
                        
                        Button comment = new Button("Comment"); 

                        AddCommentForm.addAll(lbnomAnnonce,lbdescriptionAnnonce,champCommentaire,comment);
                        AddCommentForm.show();

                        comment.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            System.out.println("Announce ID : " + an.getId());     
                            
                            int a= an.getId();
                           
                            if ((champCommentaire.getText().length() == 0)) {
                                ToastBar.showMessage( "PLEASE FILL THE FIELDS ! ", FontImage.MATERIAL_THUMB_UP);//notif  
                            
                            } else {
                                    //Commentaire c = new Commentaire(an.getId(),champCommentaire.getText());
                                    if (0==0){//new ServiceAnnonce().addComment(a, c)) {
                                       ToastBar.showMessage( "COMMENT ADDED ! ", FontImage.MATERIAL_THUMB_UP);//notif
                                    new AnnonceForm().showBack();
                                    } else {
                                        ToastBar.showMessage( "ERROR ! ", FontImage.MATERIAL_THUMB_UP);//notif
                                    
                                    }
                                }
                            new AnnonceForm().showBack();
                        }   
                    });
                }
                });
                
                /*------------------      AFFICHER LISTE COMMENTAIRE      ------------------*/
                
                commentlist.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        afficherCom(previous,an);
                    }
                });   
            }
        });
    
        this.getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            previous.showBack();
        });
    }
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
                    String line3 = mb.getTextLine3();
                    boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1 ||
                                   line2 != null && line2.toLowerCase().indexOf(text) > -1 ||
                                   line3 != null && line3.toLowerCase().indexOf(text) > -1;
                    mb.setHidden(!show);
                    mb.setVisible(show);
                }
                this.getContentPane().animateLayout(150);
            }
        }, 4);
        
        
        
        
}
    public void afficherCom(Form previous,Annonce an){
        ServiceAnnonce as = new  ServiceAnnonce();
        Form CommentListForm = new Form(BoxLayout.y());
                        System.out.println("Announce ID : " + an.getId());  
                                                  
                        int a= an.getId();
                        
                        CommentListForm.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,(ev) -> {
                        previous.showBack();
                        });

                        ArrayList<Commentaire> list = as.getComments(a);
        
                        for (Commentaire cs : list) {

                            MultiButton fourLinesIcon = new MultiButton("- " +(cs.getId()));
                            fourLinesIcon.setTextLine1("" +cs.getChampCommentaire());
                            fourLinesIcon.setTextLine2("Comment date : "+(cs.getDateCommentaire()));
                            CommentListForm.add(fourLinesIcon);
                            
                            //user = 1
                            if(cs.getUser_id() == 1){
                                
                                Button supp = new Button("Delete");
                                Button modif = new Button("Update");
                                
                                supp.addActionListener((evtt) -> {
                                    as.deleteComment(cs.getId());
                                    afficherCom(previous,an);
                                } );
                                
                                modif.addActionListener(new ActionListener() {
                                @Override
                                    public void actionPerformed(ActionEvent evt) {

                                        Form UpdateCommentForm = new Form(BoxLayout.y());
                                        TextField tfchampComment = new TextField(cs.getChampCommentaire());
                                        Button update = new Button("Update"); 

                                        UpdateCommentForm.getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK,(ev) -> {
                                        previous.showBack();
                                        });

                                        UpdateCommentForm.addAll(tfchampComment,update);
                                        UpdateCommentForm.show();

                                        update.addActionListener(new ActionListener() {
                                            @Override
                                                public void actionPerformed(ActionEvent evt) {
                                                    System.out.println("Comment ID : " + cs.getId());
                                                    
                                                    cs.setChampCommentaire(tfchampComment.getText());

                                                    as.updateComment(cs);
                                                    ToastBar.showMessage( "COMMENT UPDATED ! ", FontImage.MATERIAL_THUMB_UP);//notif
                        
                                                    System.out.println("updated! ");
                                                    
                                                }   
                                        });
                                        
                                    }
                                });

                                
                                CommentListForm.addAll(modif,supp);
                            }
                            CommentListForm.show();
                        }}
    
}