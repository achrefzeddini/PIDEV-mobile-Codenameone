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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.esprit.pidevMobile.models.Annonce;
import com.esprit.pidevMobile.utils.DataSource;
import com.esprit.pidevMobile.utils.Statics;
import java.util.Date;
import com.esprit.pidevMobile.models.Commentaire;
import com.esprit.pidevMobile.utils.CurrentUser;


/**
 *
 * @author elhak
 */
public class ServiceAnnonce {
    
    private ConnectionRequest request;
    CurrentUser CU = new CurrentUser();
    private boolean responseResult;

    public ArrayList<Annonce> annonces;
    public ArrayList<Commentaire> commentaires; 
    
    public ServiceAnnonce() {
        request = DataSource.getInstance().getRequest();
    }    
    //OK
    public boolean addAnnonce(Annonce a) {
        
        String url = Statics.BASE_URL + "/annonces/new?nomAnnonce=" + a.getNomAnnonce() + "&descriptionAnnonce=" + a.getDescriptionAnnonce() ;
 
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
    //OK   
    public boolean updateAnnonce(Annonce a) {
        
       String url = Statics.BASE_URL + "/annonces/update?id=" + a.getId()+ "&nomAnnonce=" + a.getNomAnnonce() + "&descriptionAnnonce=" + a.getDescriptionAnnonce() ;
   
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
    //OK
    public ArrayList<Annonce> getAllAnnonces() {
        
        String url = Statics.BASE_URL + "/annonces/all";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                annonces = parseAnnonces(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return annonces;
    }
    //OK
    public ArrayList<Annonce> getMyAnnonces() {
        
        String url = Statics.BASE_URL + "/annonces/mine";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                annonces = parseAnnonces(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return annonces;
    }
    //OK
    public void deleteAnnonce(int idAnnonce){
        ConnectionRequest con = new ConnectionRequest();
        String url = Statics.BASE_URL + "/annonces/delete/"+idAnnonce;
        con.setUrl(url);
        System.out.println("test");
        System.out.println(url);
        con.addResponseListener((e) -> {        
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        System.out.println("test");
        con.setFailSilently(true);
        NetworkManager.getInstance().addToQueueAndWait(con);
      
    }
    //OK
    public ArrayList<Annonce> parseAnnonces(String jsonText) {
        try {
            annonces = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> annoncesListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) annoncesListJson.get("root");
            for (Map<String, Object> obj : list) {
                int idAnnonce = (int)Float.parseFloat(obj.get("id").toString());
                String nomAnnonce = obj.get("nomAnnonce").toString();
                String descriptionAnnonce = obj.get("descriptionAnnonce").toString();
                Map<String, Object> dates = null;
                        dates = (Map<String, Object>) obj.get("dateAnnonce");
                   Date datec = new Date((long) Float.parseFloat(dates.get("timestamp").toString()) * 1000);
                annonces.add(new Annonce( idAnnonce, nomAnnonce, descriptionAnnonce, datec));
            }

        } catch (IOException ex) {
        }

        return annonces;
    }
  
/* -------------------------   COMMENTAIIIIIIIIRE -----------------------------------*/  
    //OK
    public boolean addComment(int a , Commentaire c) {
        
        String url = Statics.BASE_URL + "/commentaires/newC/"+a+"?champCommentaire=" + c.getChampCommentaire();
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
    //OK
    public ArrayList<Commentaire> getComments(int a) {
        
        String url = Statics.BASE_URL + "/commentaires/allC/"+a;

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                commentaires = parseCommentaire(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return commentaires;
    }
    //OK
    public ArrayList<Commentaire> parseCommentaire(String jsonText) {
        try {
            commentaires = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> annoncesListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) annoncesListJson.get("root");
            for (Map<String, Object> obj : list) {
                int idCommentaire = (int)Float.parseFloat(obj.get("id").toString());
                //int annonce_id = (int)Float.parseFloat(obj.get("annonce_id").toString());
                //int user_id = (int)Float.parseFloat(obj.get("user_id").toString());
                String champCommentaire = obj.get("champCommentaire").toString();
                Map<String, Object> dates = null;
                    dates = (Map<String, Object>) obj.get("dateCommentaire");
                   Date datec = new Date((long) Float.parseFloat(dates.get("timestamp").toString()) * 1000);
                //Map<String, Object> user = (Map<String, Object>) obj.get("user");
                int user_id = CU.getIdCurrentUser();//(int)Float.parseFloat(user.get("id").toString());
                
                   commentaires.add(new Commentaire(idCommentaire/*,annonce_id,user_id*/,champCommentaire, datec,user_id));
            }

        } catch (IOException ex) {
        }

        return commentaires;
    }   
    //OK
    public void deleteComment(int idCom){
        ConnectionRequest con = new ConnectionRequest();
        String url = Statics.BASE_URL + "/commentaires/delete/"+idCom;
        con.setUrl(url);
        System.out.println("test");
        System.out.println(url);
        con.addResponseListener((e) -> {        
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        System.out.println("test");
        con.setFailSilently(true);
        NetworkManager.getInstance().addToQueueAndWait(con);
      
    }
    //OK   
    public boolean updateComment(Commentaire c) {
        
       String url = Statics.BASE_URL + "/commentaires/update?id=" + c.getId()+ "&champCommentaire=" + c.getChampCommentaire() ;
   
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
  
    
    
public void sendMail() {

        Message m = new Message("<html><body>Check out <a href=\"https://www.codenameone.com/\">Codename One</a></body></html>");
        m.setMimeType(Message.MIME_HTML);
// notice that we provide a plain text alternative as well in the send method
        boolean success = m.sendMessageViaCloudSync("Codename One", "br.rassil@gmail.com", "Name Of User", "Message Subject",
                "Check out Codename One at https://www.codenameone.com/");
        System.out.println("success: " + success);
    }
    
    
    
}






