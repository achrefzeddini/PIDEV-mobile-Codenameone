/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidevMobile.services;

import com.esprit.pidevMobile.models.Event;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.ui.events.ActionListener;
import com.esprit.pidevMobile.utils.CurrentUser;
import com.esprit.pidevMobile.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jha
 */
public class ServiceMyEvent {
    
    CurrentUser CU = new CurrentUser();
    int idUser = CU.getIdCurrentUser();
    
    public ArrayList<Event> parseListMyEventsJson(String json) throws ParseException, IOException  {

        ArrayList<Event> listEvts = new ArrayList<>();

      try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilité de new CharArrayReader(json.toCharArray())
            
            La méthode parse json retourne une MAP<String,Object> ou String est 
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets 
                    c'est la clé définissant le tableau de tâches.
            */
            Map<String, Object> product = j.parseJSON(new CharArrayReader(json.toCharArray()));
       
                       
            
            /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche                
            */
            List<Map<String, Object>> list = (List<Map<String, Object>>) product.get("root");
            

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                Event c = new Event();

                
                float id = Float.parseFloat(obj.get("idevent").toString());
                c.setIdEvent((int) id) ;
                float nbrp = Float.parseFloat(obj.get("nbrplaces").toString());
                c.setNbrplaces((int) nbrp) ;
                c.setTitre((String) obj.get("titre"));
                c.setLocalisation((String) obj.get("localisation"));
                c.setHdebut(Float.parseFloat(obj.get("hdebut").toString()));
                c.setHfin(Float.parseFloat(obj.get("hfin").toString()));
                c.setPrix(Float.parseFloat(obj.get("prix").toString()));
                
                
                
                listEvts.add(c);

            }
              } catch (IOException ex) {
            
         } 

     
        
        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */
       // System.out.println(listCotisation);
        return listEvts;
    }
     
     ArrayList<Event> listEvts= new ArrayList<>();
    
    public ArrayList<Event> getListMyEvents() throws ParseException {       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl(Statics.BASE_URL+"/gestionevent/wsGetMyEvents?id="+idUser);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                
                try {
                    listEvts = parseListMyEventsJson(new String(con.getResponseData()));
                } catch (ParseException ex) {
                    //Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    //Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                
            }
        });
      
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEvts;
    }
}
