/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidevMobile.forms;

import com.codename1.l10n.DateFormatPatterns;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.esprit.pidevMobile.models.Season;
import com.esprit.pidevMobile.services.SeasonService;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;


/**
 *
 * @author hp
 */
public class FormNewSeason extends Form {
    Form f;
    Resources res;
    public FormNewSeason(Form previous) {
        super("Add a new Season", BoxLayout.y());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, evt -> new HomeForm(res).showBack());
        SeasonService as = new SeasonService();
        ArrayList<Season> list = as.getAllSeasons();
        Label lbnom = new Label("Name");
        Label lbstart = new Label("Start");
        Label lbfinish = new Label("Finish");
        Label lbdesc = new Label("Description");

        TextField tfnom = new TextField();
        Picker start = new Picker();
        start.setType(Display.PICKER_TYPE_DATE);
        start.setDate(new Date());
        Picker finish = new Picker();
        finish.setType(Display.PICKER_TYPE_DATE);
        finish.setDate(new Date());
        TextField tfdesc = new TextField();

        
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                        Date datecreation = new Date(System.currentTimeMillis());
                        SimpleDateFormat format = new SimpleDateFormat(DateFormatPatterns.ISO8601);
                        
                        System.out.println(start.getDate());
                        System.out.println(finish.getDate());
        
        Button btn = new Button("Add Season");
  
        btn.addActionListener((evt) -> {
            if ((tfnom.getText().length() == 0) || (tfdesc.getText().length() == 0)) {
                Dialog.show("Alert", "Please fill all the fields", "OK", null);
            } else {
                try {
                    Season t = new Season(tfnom.getText(), start.getDate(), finish.getDate(), tfdesc.getText());
                    if (new SeasonService().addSeason(t)) {
                        Dialog.show("SUCCESS", "Season Added", "OK", null);
                        new FormSeason(f).show();
                        

                    } else {
                        Dialog.show("SUCCESS", "nope", "OK", null);
                    }
                } catch (NumberFormatException e) {
                    Dialog.show("ERROR", "Wrong", "OK", null);
                } catch (URISyntaxException ex) {

                } catch (IOException ex) {
 
                }

           }
        });

        this.addAll(lbnom, tfnom, lbstart, start,lbfinish, finish, lbdesc, tfdesc, btn);

        this.getToolbar().addCommandToLeftBar("Return", null, (evt) -> {
            previous.showBack();
        });
    }
    
}
