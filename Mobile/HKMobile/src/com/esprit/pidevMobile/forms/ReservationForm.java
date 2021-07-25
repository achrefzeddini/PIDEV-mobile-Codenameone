/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidevMobile.forms;

import com.esprit.pidevMobile.services.ServiceReservation;
import com.codename1.io.ConnectionRequest;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.spinner.Picker;
import java.util.Date;
import com.codename1.messaging.Message;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.util.Resources;
import com.esprit.pidevMobile.models.Reservation;



/**
 *
 * @author jha
 */
public class ReservationForm extends Form{
    public static int idevent;
    public static int iduser;
    Resources res;
    public ReservationForm() {
        super("Reservation");
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, evt -> new HomeForm(res).showBack());
        
        TextField nom = new TextField();
        Picker date = new Picker();
     TextField qte = new TextField();
     ComboBox<String> type = new ComboBox<String>();
     ComboBox<String> seat = new ComboBox<String>();
        type.addItem("type 1");
        type.addItem("type 1");
        type.addItem("type 1");
        
        seat.addItem("seat 1");
        seat.addItem("seat 2");
        seat.addItem("seat 3");
        add(new Label("Nom"));
        add(nom);
        add(new Label("Date"));
        add(date);
        add(new Label("qte"));
        add(qte);
        add(new Label("Type"));
        add(type);
        add(new Label("Seat"));
        add(seat);
        Button confirmer = new Button("confirmer");
        add(confirmer);
                    Button imprimer =new Button("PDF");
                    add(imprimer);

        Reservation r = new Reservation();
        confirmer.addActionListener(l->{
            
            Date d = new Date();
            d = date.getDate();
            DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");  
            String strDate = dateFormat.format(d);
            r.setDate(strDate);
            r.setIdEvent(idevent);
            r.setIdUser(iduser);
            r.setNom(nom.getText());
            r.setQte((int)Float.parseFloat(qte.getText()));
            r.setSeat(seat.getSelectedItem());
            r.setType(type.getSelectedItem());
            ServiceReservation sr = new ServiceReservation();
            try {
                sr.reserver(r);
                      Message m = new Message("cher client votre reservation a ete confirmée");
                    Display.getInstance().sendMessage(new String[] {"chaima.sebai1998@gmail.com"},"Description:"+qte.getText() , m);
                    System.out.println("Mail succes"); 
            } catch (ParseException ex) {
               // Logger.getLogger(Reservation.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        });
         imprimer.addActionListener(l->{
             try{
            ConnectionRequest req = new ConnectionRequest();
            Display.getInstance().execute("http://127.0.0.1:8000/imprimerM/"+ r.getIdEvent());
        }catch ( Exception e)
        {}
    });
        
    }
    
}
