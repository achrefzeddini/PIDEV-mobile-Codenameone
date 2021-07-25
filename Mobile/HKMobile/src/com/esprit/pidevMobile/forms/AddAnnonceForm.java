/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidevMobile.forms;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.esprit.pidevMobile.models.Annonce;
import com.esprit.pidevMobile.services.ServiceAnnonce;


import com.codename1.components.ToastBar; //notif
import com.codename1.io.rest.Response;
import com.codename1.io.rest.Rest;
import com.codename1.ui.util.Resources;
import static com.esprit.pidevMobile.models.Annonce.ACCOUNT_SID;
import static com.esprit.pidevMobile.models.Annonce.AUTH_TOKEN;


//sms
/*import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;*/
import java.util.Map;

/**
 *
 * @author elhak
 */
public class AddAnnonceForm extends Form {
    Resources res;
    Form f;
    public AddAnnonceForm(Form previous) {
                super("Add a new annonce", BoxLayout.y());
getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, evt -> new HomeForm(res).showBack());
        TextField tfNomAnnonce = new TextField(null, "Annonce name");
        TextField tfDescriptionAnnonce = new TextField(null, "Express yourself !");
        Button btn = new Button("Add the annonce");

        btn.addActionListener((evt) -> {
            if ((tfNomAnnonce.getText().length() == 0) || (tfDescriptionAnnonce.getText().length() == 0)) {
                ToastBar.showMessage( "PLEASE FILL THE FIELDS ! ", FontImage.MATERIAL_THUMB_DOWN);//notif      
            } else {
                    Annonce a = new Annonce(tfNomAnnonce.getText(),tfDescriptionAnnonce.getText());
                    if (new ServiceAnnonce().addAnnonce(a)) {
                        
                        /* -----------   SMS   -----------   */
                        Response<Map> v = Rest.post("https://api.twilio.com/2010-04-01/Accounts/" + ACCOUNT_SID + "/Messages.json").
                            queryParam("To", "+21624058303").
                            queryParam("From", "+16573124743").
                            queryParam("Body", "SUCCES").
                            basicAuth(ACCOUNT_SID, AUTH_TOKEN).//header("Authorization", "Basic " + Base64.encodeNoNewline((ACCOUNT_SID + ":" + AUTH_TOKEN).getBytes())).
                            getAsJsonMap();
                        
                        /* -----------   NOTIF   -----------   */
                        ToastBar.showMessage( "ANNOUNCE ADDED ! ", FontImage.MATERIAL_THUMB_UP); 
                        new AnnoncesListForm(f).showBack();
                    } else {
                        ToastBar.showMessage( "ERROR ! ", FontImage.MATERIAL_THUMB_DOWN);//notif                          
                    }
            }
        });

        this.addAll(tfNomAnnonce, tfDescriptionAnnonce, btn);

        this.getToolbar().addMaterialCommandToLeftBar("",FontImage.MATERIAL_ARROW_BACK, (evt) -> {
            previous.showBack();
        });
    }
}


