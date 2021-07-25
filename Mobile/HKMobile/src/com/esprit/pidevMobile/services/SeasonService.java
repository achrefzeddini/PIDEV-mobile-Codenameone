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
import com.codename1.l10n.ParseException;
import com.codename1.ui.events.ActionListener;
import com.esprit.pidevMobile.models.Season;
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
public class SeasonService {
     private ConnectionRequest request;

    private boolean responseResult;
    public ArrayList<Season> seasons;

    public SeasonService() {
        request = DataSource.getInstance().getRequest();
    }

    public boolean addSeason(Season seasons) {
        String url = Statics.BASE_URL + "/addseason?nom=" + seasons.getNom() + "&start=" + seasons.getStart() + "&finish=" + seasons.getFinish() + "&description=" + seasons.getDescription();

        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200; // Code HTTP 200 OK
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return responseResult;
    }

    public ArrayList<Season> getAllSeasons() {
        String url = Statics.BASE_URL + "/allseason";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    seasons = parseSeasons(new String(request.getResponseData()));
                } catch (ParseException ex) {
                    
                }
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return seasons;
    }

    public ArrayList<Season> parseSeasons(String jsonText) throws ParseException {
        try {
            seasons = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> seasonsListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) seasonsListJson.get("root");
            for (Map<String, Object> obj : list) {
                int id = (int)Float.parseFloat(obj.get("id").toString());
                String nom = obj.get("nom").toString();
                Map<String, Object> dates = null;
                        dates = (Map<String, Object>) obj.get("start");
                Date datestart = new Date((long) Float.parseFloat(dates.get("timestamp").toString()) * 1000);
                Map<String, Object> datef = null;
                        datef = (Map<String, Object>) obj.get("finish");
                Date datefinish = new Date((long) Float.parseFloat(datef.get("timestamp").toString()) * 1000);
                
//                String start = obj.get("start").toString();
//                Date dates=new SimpleDateFormat("dd/MM/yyyy").parse(start);
//                String finish = obj.get("finish").toString();
//                Date datef=new SimpleDateFormat("dd/MM/yyyy").parse(finish);
                String description = obj.get("description").toString();
 
                seasons.add(new Season(id, nom, datestart, datefinish, description));
            }

        } catch (IOException ex) {
        }

        return seasons;
    }
    public void supprimerSeason(int id){
                ConnectionRequest con = new ConnectionRequest();
        String Url = Statics.BASE_URL+"/deleteSeason/"+id;
        con.setUrl(Url);
        System.out.println("tt");
        con.addResponseListener((e) -> {
          
            String str = new String(con.getResponseData());
     
 
            
        });
        System.out.println("bom bom bom");
        con.setFailSilently(true);
        NetworkManager.getInstance().addToQueueAndWait(con);
      
    }
    public void updateSeason(Season s){
      
        ConnectionRequest con = new ConnectionRequest();
        String Url = Statics.BASE_URL+"/updateseason?id=" + s.getId() + "&nom=" + s.getNom() + "&start=" + s.getStart() + "&finish=" + s.getFinish() + "&description=" + s.getDescription();
        con.setUrl(Url);

        System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
//    public void sendMail() {
//
//        Message m = new Message("<html><body>Check out <a href=\"https://www.codenameone.com/\">Codename One</a></body></html>");
//        m.setMimeType(Message.MIME_HTML);
//
//// notice that we provide a plain text alternative as well in the send method
//        boolean success = m.sendMessageViaCloudSync("Codename One", "br.rassil@gmail.com", "Name Of User", "Message Subject",
//                "Check out Codename One at https://www.codenameone.com/");
//        System.out.println("success: " + success);
//    }
    
}
