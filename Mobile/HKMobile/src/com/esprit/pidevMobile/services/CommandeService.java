/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidevMobile.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.messaging.Message;
import com.codename1.ui.events.ActionListener;
import com.esprit.pidevMobile.models.Commande;
import com.esprit.pidevMobile.utils.DataSource;
import com.esprit.pidevMobile.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hp
 */
public class CommandeService {

    private ConnectionRequest request;

    private boolean responseResult;
    public ArrayList<Commande> commandes;

    public CommandeService() {
        request = DataSource.getInstance().getRequest();
    }

//    public boolean addAnimal(Animal animals) {
//        String url = Statics.BASE_URL + "/newanimalapi?idA=" + animals.getIdA() + "&race=" + animals.getRace() + "&saison=" + animals.getSaison() + "&place=" + animals.getPlace() + "&image=" + animals.getImage() + "&hunted=" + animals.getHunted();
//
//        request.setUrl(url);
//        request.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
//                request.removeResponseListener(this);
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(request);
//
//        return responseResult;
//    }

    public ArrayList<Commande> getAllCommandes() {
        String url = Statics.BASE_URL + "/allcommande";

        request.setUrl(url);
        //request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                commandes = parseCommandes(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return commandes;
    }

    public ArrayList<Commande> parseCommandes(String jsonText) {
        try {
            commandes = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> animalsListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) animalsListJson.get("root");
            for (Map<String, Object> obj : list) {
                int id = (int)Float.parseFloat(obj.get("id").toString());
                String produit = obj.get("produit").toString();
                   Map<String, Object> dates = null;
                        dates = (Map<String, Object>) obj.get("date");
                   Date datec = new Date((long) Float.parseFloat(dates.get("timestamp").toString()) * 1000);
                double price = (double)Float.parseFloat(obj.get("price").toString());
                int state = (int)Float.parseFloat(obj.get("state").toString());
              
//                int season_id = (int)Float.parseFloat(obj.get("season_id").toString());
                commandes.add(new Commande(id, produit, datec, price, state));
            }

        } catch (IOException ex) {
        }

        return commandes;
    }
    public void updateCommande(int id){
      
        ConnectionRequest con = new ConnectionRequest();
        String Url = Statics.BASE_URL+"/updateCommande/" + id;
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    public void supprimerCommande(int id){
                ConnectionRequest con = new ConnectionRequest();
        String Url = Statics.BASE_URL+"/deleteCommande/"+id;
        con.setUrl(Url);
        System.out.println(Url);
        con.addResponseListener((e) -> {
          
            String str = new String(con.getResponseData());
     
            System.out.println(str);
            
        });
        con.setFailSilently(true);
        NetworkManager.getInstance().addToQueueAndWait(con);
      
    }
        public void sendMail() {

        Message m = new Message("<html><body>Check out <a href=\"https://www.codenameone.com/\">Codename One</a></body></html>");
        m.setMimeType(Message.MIME_HTML);

// notice that we provide a plain text alternative as well in the send method
        boolean success = m.sendMessageViaCloudSync("Codename One", "br.rassil@gmail.com", "Name Of User", "Message Subject",
                "Check out Codename One at https://www.codenameone.com/");
        System.out.println("success: " + success);
    }
    
}



