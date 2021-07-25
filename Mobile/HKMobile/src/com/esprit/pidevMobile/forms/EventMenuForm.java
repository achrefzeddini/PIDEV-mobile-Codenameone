/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidevMobile.forms;

import com.codename1.l10n.ParseException;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;



public class EventMenuForm extends Form{
    Resources res;
    public EventMenuForm() {
    

    super("Event", BoxLayout.y());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, evt -> new HomeForm(res).showBack());
        Button eventsBTN = new Button("Events");
        Button myeventsBTN = new Button("My Events");
        eventsBTN.addActionListener((evt) -> {
        try {
            new EventsForm().show();
        } catch (ParseException ex) {
            //Logger.getLogger(EventMenuForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        });
        myeventsBTN.addActionListener((evt) -> {
        try {
            new MyEventsForm().show();
        } catch (ParseException ex) {
            //Logger.getLogger(EventMenuForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        });
        this.addAll(new Label("Choose an option :"), myeventsBTN, eventsBTN );
    }
    
    
    
}
