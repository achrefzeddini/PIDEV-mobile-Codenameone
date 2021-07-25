/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidevMobile.forms;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
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
public class FormNewAnimal extends Form{
    Form f;
    Resources res;
    public FormNewAnimal(Form previous) {
        super("Add a new Animal", BoxLayout.y());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, evt -> new HomeForm(res).showBack());
        SeasonService as = new SeasonService();
        ArrayList<Season> list = as.getAllSeasons();
        Label lbref = new Label("Reference");
        Label lbrace = new Label("Race");
        Label lbsaison = new Label("Season");
        Label lbplace = new Label("Place");
        Label lbimage = new Label("Picture");
        Label lbhunted = new Label("Times Hunted");
        TextField tfidA = new TextField();
        TextField tfrace = new TextField();
        ComboBox cb = new ComboBox();
         for (Season an : list){
           cb.addItem(an.getNom());  
         }
        TextField tfplace = new TextField();
        TextField tfimage = new TextField();
        TextField tfhunted = new TextField("0");

        Button btn = new Button("Add Animal");

        btn.addActionListener((evt) -> {
           
        
            for(int i=0;i<tfidA.getText().length();i++){
        
            if(!Character.isDigit(tfidA.getText().charAt(i))){
           Dialog.show("Alert", "Reference must be a number", "OK", null);
          i=tfidA.getText().length();
            }}
           
            if ((tfidA.getText().length() == 0) || (tfrace.getText().length() == 0) || (tfplace.getText().length() == 0) || (tfhunted.getText().length() == 0) || (tfimage.getText().length() == 0) ) {
                Dialog.show("Alert", "Please fill all the fields", "OK", null);
            } else {
                try {
                    Animal t = new Animal(Integer.parseInt(tfidA.getText()), tfrace.getText(), cb.getSelectedItem().toString(), tfplace.getText(), tfimage.getText(), Integer.parseInt(tfhunted.getText()));
                    if(controle_number(tfidA.getText())){
                    if (new AnimalService().addAnimal(t) ) {
                        Dialog.show("SUCCESS", "Animal Added ", "OK", null);
                       
                        new FormAnimal(f).show();

                    } else {
                       
                        Dialog.show("Alert", "There was a problem", "OK", null);
                    } 
                    }else {
                    Dialog.show("Alert", "Reference Already Exists", "OK", null);
                    }
                } catch (NumberFormatException e) {
                    
                } catch (URISyntaxException ex) {
                    
                } catch (IOException ex) {
              
                }

           }
             });

        this.addAll(lbref, tfidA, lbrace, tfrace,lbsaison, cb, lbplace, tfplace, lbimage, tfimage, lbhunted, tfhunted, btn);

        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previous.showBack();
        });
    }
    
     private boolean controle_number(String s)
    {
                
          
            AnimalService ans = new AnimalService();
            ArrayList<Animal> lista = ans.getAllAnimals();
            for (Animal ansa : lista){
            if(ansa.getIdA()==Integer.parseInt(s)){
               return false;
            }
         }
        return true;
    }
}
