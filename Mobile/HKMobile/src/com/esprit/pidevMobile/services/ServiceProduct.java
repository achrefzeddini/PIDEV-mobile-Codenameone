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
import com.codename1.ui.events.ActionListener;
import com.esprit.pidevMobile.models.Product;
import com.esprit.pidevMobile.utils.CurrentUser;
import com.esprit.pidevMobile.utils.DataSource;
import com.esprit.pidevMobile.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Dorsaf
 */
public class ServiceProduct {
    
     private ConnectionRequest request;
     CurrentUser CU = new CurrentUser();

    private boolean responseResult;
    public ArrayList<Product> products;

    public ServiceProduct() {
        request = DataSource.getInstance().getRequest();
    }
    
    
    public boolean addProduct(Product p){
        String s= p.getPhoto();
        String replaced = s.replace('/', '*');
        String url = Statics.BASE_URL+"/products/new/"+p.getName()+"/"+p.getPrice()+"/"+p.getDescription()+"/"+replaced+"/"+CU.getIdCurrentUser();
        System.out.println(url);
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
    
    
    public boolean updateProduct(Product p){
        String s= p.getPhoto();
        String replaced = s.replace('/', '*');
        String url = Statics.BASE_URL+"/product/update/"+p.getId()+"/"+p.getName()+"/"+p.getPrice()+"/"+p.getDescription()+"/"+replaced;
        
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
    
    
     public boolean deleteProduct(int id){
      
        String url = Statics.BASE_URL+"/products/delete/"+id;
        
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
    
    
    public ArrayList<Product> getAllProducts() {
        String url = Statics.BASE_URL + "/products/all";

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                products = parseproducts(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return products;
    }
    
    public ArrayList<Product> getUserProducts() {
        String url = Statics.BASE_URL + "/products/all/"+CU.getIdCurrentUser();

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                products = parseproducts(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return products;
    }
    
    
    public ArrayList<Product> viewProducts(int num) {
        String url = Statics.BASE_URL + "/products/find/"+num;

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                products = parseproducts(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return products;
    }
    
    
    public ArrayList<Product> parseproducts(String jsonText) {
        try {
            products = new ArrayList<>();

            JSONParser jp = new JSONParser();
            Map<String, Object> tasksListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");
            for (Map<String, Object> obj : list) {
                int id = (int)Float.parseFloat(obj.get("id").toString());
                float price = Float.parseFloat(obj.get("price").toString());
                String name = obj.get("name").toString();
                String description = obj.get("description").toString();
                String photo = obj.get("photo").toString();
                products.add(new Product(id, name, price, description, photo, 1));
            }

        } catch (IOException ex) {
        }

        return products;
    }
    
   
}
