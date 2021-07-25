/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidevMobile.services;


/**
 *
 * @author hp
 */
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.esprit.pidevMobile.models.Animal;
import com.esprit.pidevMobile.utils.DataSource;
import com.esprit.pidevMobile.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AnimalService {

    private ConnectionRequest request;

    private boolean responseResult;
    public ArrayList<Animal> animals;

    public AnimalService() {
        request = DataSource.getInstance().getRequest();
    }

    public boolean addAnimal(Animal animals) {
        String url = Statics.BASE_URL + "/newanimalapi?idA=" + animals.getIdA() + "&race=" + animals.getRace() + "&saison=" + animals.getSaison() + "&place=" + animals.getPlace() + "&image=" + animals.getImage() + "&hunted=" + animals.getHunted();

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

    public ArrayList<Animal> getAllAnimals() {
        String url = Statics.BASE_URL + "/animals/all";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                animals = parseAnimals(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return animals;
    }

    public ArrayList<Animal> parseAnimals(String jsonText) {
        try {
            animals = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> animalsListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) animalsListJson.get("root");
            for (Map<String, Object> obj : list) {
                int id = (int)Float.parseFloat(obj.get("id").toString());
                int idA = (int)Float.parseFloat(obj.get("idA").toString());
                String race = obj.get("race").toString();
                String saison = obj.get("saison").toString();
                String place = obj.get("place").toString();
                String image = obj.get("image").toString();
                int hunted = (int)Float.parseFloat(obj.get("hunted").toString());
//                int season_id = (int)Float.parseFloat(obj.get("season_id").toString());
                animals.add(new Animal(id, idA, race, saison, place, image, hunted));
            }

        } catch (IOException ex) {
        }

        return animals;
    }
     public void supprimerAnimal(int id){
                ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/HuntKingdom/web/app_dev.php/deleteAnimal/"+id;
        con.setUrl(Url);
        System.out.println("tt");
        System.out.println(Url);
        con.addResponseListener((e) -> {
          
            String str = new String(con.getResponseData());
     
            System.out.println(str);
            
        });
        System.out.println("bom bom bom");
        con.setFailSilently(true);
        NetworkManager.getInstance().addToQueueAndWait(con);
      
    }
    public void updateAnimal(Animal animals){
      
        ConnectionRequest con = new ConnectionRequest();
        String Url = Statics.BASE_URL+"/updateanimalapi?idA=" + animals.getIdA() + "&race=" + animals.getRace() + "&saison=" + animals.getSaison() + "&place=" + animals.getPlace() + "&image=" + animals.getImage() + "&hunted=" + animals.getHunted();
        con.setUrl(Url);

        System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
}


