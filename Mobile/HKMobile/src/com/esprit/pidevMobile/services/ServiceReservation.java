/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidevMobile.services;

import com.esprit.pidevMobile.models.Reservation;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.ui.events.ActionListener;
import com.esprit.pidevMobile.utils.Statics;

/**
 *
 * @author jha
 */
public class ServiceReservation {
    
    
    

    public void reserver(Reservation r) throws ParseException {       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(Statics.BASE_URL+"/gestionevent/reserver?nom="+r.getNom()+"&date="+r.getDate()+"&qte="+r.getQte()+"&type="+r.getType()+"&seat="+r.getSeat()+"&idEvent="+r.getIdEvent()+"&idUser="+r.getIdUser());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                
                
                
                
                
            }
        });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
        
    }
}
