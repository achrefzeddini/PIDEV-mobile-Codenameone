/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidevMobile.forms;

import com.codename1.components.ImageViewer;
import com.codename1.components.MultiButton;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.pidevMobile.models.Animal;
import com.esprit.pidevMobile.models.Season;
import com.esprit.pidevMobile.services.AnimalService;
import com.esprit.pidevMobile.services.SeasonService;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;




/**
 *
 * @author hp
 */
public class FormAnimal extends Form {
    Form f;
    com.codename1.ui.Image imgs;
    ImageViewer imgv;
    private Resources theme;
 public FormAnimal(Form previous) throws URISyntaxException, IOException {
        super("Animals list", BoxLayout.y());
        AnimalService as = new AnimalService();
        ArrayList<Animal> list = as.getAllAnimals();
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, evt -> new HomeForm(theme).showBack());
        for (Animal an : list) {
//         Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
//         Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
//         Container C3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
//
//            SpanLabel ida = new SpanLabel("Reference : " +String.valueOf(an.getIdA()));
//            SpanLabel race = new SpanLabel("Race : " +an.getRace());
//            SpanLabel saison = new SpanLabel("Season : "+an.getSaison());
//            SpanLabel place = new SpanLabel("Place :"+an.getPlace());
//            SpanLabel hunted = new SpanLabel("Times hunted :"+String.valueOf(an.getHunted()));
           MultiButton mb = new MultiButton();
           mb.setTextLine1(an.getRace());
           mb.setTextLine2(an.getSaison());
           mb.setTextLine3(an.getPlace());
      
      
          


            String url = an.getImage();
            int deviceWidth = Display.getInstance().getDisplayWidth();
            Image placeholder = Image.createImage( deviceWidth/2,  deviceWidth/2, 0xbfc9d2);
            EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
            Image i = URLImage.createToStorage(encImage, "fileNameInStoragez" + an.getImage() + an.getIdA(),url, URLImage.RESIZE_SCALE);
//            EncodedImage enc = 
//                EncodedImage.
//                        createFromImage(
//                                theme.getImage("icon.png")
//                                ,false);
//          imgs = URLImage.createToStorage(enc, "fi.png", url, URLImage.RESIZE_SCALE);
//                
//                imgs = imgs.scaled(350, 350);
                imgv = new ImageViewer();
                imgv.setImage(i);
                 mb.setIcon(i);
//                 C2.add(mb);
//                 C2.add(btnUpdate);
//                 C2.add(btnSupprimer);

                        mb.addPointerReleasedListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent evt) {
               SeasonService as = new SeasonService();
                 ArrayList<Season> list = as.getAllSeasons();
                 Form update = new Form(BoxLayout.y());
                 TextField ref = new TextField(String.valueOf(an.getIdA()));
                 ref.setEditable(false);
                 TextField race = new TextField(an.getRace());
                 ComboBox cb = new ComboBox();
                 cb.addItem(an.getSaison());
                 for (Season asn : list){
                     if(!an.getSaison().equals(asn.getNom()))
                     cb.addItem(asn.getNom());  
                 }
                 TextField place = new TextField(an.getPlace());
                 TextField image = new TextField(an.getImage());
                 TextField hunted = new TextField(String.valueOf(an.getHunted()));
                 Button updateanimal = new Button("Update Animal");
                 Button btnSupprimer = new Button("Delete");
                                 btnSupprimer.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if(Dialog.show("Confirmation", "Are you Sure you Want to Delete "+ an.getRace() , "OK", "Cancel")){
                            AnimalService as = new AnimalService();
                            as.supprimerAnimal(an.getId());
                           
                            Dialog.show("SUCCESS", "Animal Successfully Deleted", "OK", null);
                            try {
                                new FormAnimal(f).show();
//f.refreshTheme();
                            } catch (URISyntaxException ex) {
                        
                            } catch (IOException ex) {
               
                            }
                }

                    }
                });
                         updateanimal.addActionListener((evt1) -> {
      
                     Animal t = new Animal(Integer.parseInt(ref.getText()), race.getText(), cb.getSelectedItem().toString(), place.getText(), image.getText(), Integer.parseInt(hunted.getText()));
                         new AnimalService().updateAnimal(t) ;
                        Dialog.show("SUCCESS", "Animal Updated", "OK", null);
                        

                    {
                        
                         try {
                             new FormAnimal(f).show();
                         } catch (URISyntaxException ex) {

                         } catch (IOException ex) {
                       
                         }
                    }
                
           
        });
                 update.add(ref);
                 update.add(race);
                 update.add(cb);
                 update.add(place);
                 update.add(image);
                 update.add(hunted);
                 update.add(updateanimal);
                 update.add(btnSupprimer);
                 update.getToolbar().addCommandToLeftBar("Return", null, (evt2) -> {
                     try {
                         new FormAnimal(f).show();
                     } catch (URISyntaxException ex) {
                     } catch (IOException ex) {
                     }
                });
                 update.show();

             }
             
         });
                   
//            C2.add(ida);
//            C2.add(race);
//            C2.add(saison);
//            C2.add(place);
//            C2.add(hunted);
//            C1.add(imgv);
//            C1.add(C2);
//            C3.add(C1);
//            C3.add(btnUpdate);
//            C3.add(btnSupprimer);

            this.add(mb);
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
this.show();
//
//        this.add(new SpanLabel(new AnimalService().getAllAnimals().toString()));
        this.getToolbar().addCommandToOverflowMenu("Add Animal", null, (evt) -> {
            new FormNewAnimal(this).show();
        });
        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
             new HomeForm(theme).show();
        });
    }
     public Form getF() {
        return f;
    }
}