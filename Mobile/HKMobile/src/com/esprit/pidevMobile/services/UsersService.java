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
import java.util.List;
import com.codename1.ui.events.ActionListener;
import com.esprit.pidevMobile.models.Users;
import com.esprit.pidevMobile.utils.DataSource;
import com.esprit.pidevMobile.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author Testouri Mohamed
 */
public class UsersService {

    private ConnectionRequest request;
    private boolean responseResult;
    private ArrayList<Users> users;

    public UsersService() {
        request = DataSource.getInstance().getRequest();
    }

    public boolean checkLogin(String username, String password) {
        String url = Statics.BASE_URL + "/user/login?username=" + username + "&password=" + password;
        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                responseResult = request.getResponseCode() == 200;
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        System.out.println(responseResult);
        return responseResult;
    }

    //parsing user
    public ArrayList<Users> parseUsers(String jsonText) {
        try {
            users = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> usersListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> usersList = (List<Map<String, Object>>) usersListJson.get("root");

            for (Map<String, Object> obj : usersList) {
                int id = (int) Float.parseFloat(obj.get("id").toString());
                String firstname = obj.get("firstName").toString();
                String lastname = obj.get("lastName").toString();
                int phone = (int) Float.parseFloat(obj.get("phone").toString());
                String email = obj.get("email").toString();
                String username = obj.get("username").toString();
                users.add(new Users(id, firstname, lastname, phone, email, username));
            }
        } catch (IOException ex) {
        }
        return users;
    }

    //retrieving connected user
    public ArrayList<Users> getUser(String username, String password) {
        String url = Statics.BASE_URL + "/user/login?username=" + username + "&password=" + password;
        request.setUrl(url);
        //req.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                users = parseUsers(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        return users;
    }

    public void register(Users usr) {
        String url = Statics.BASE_URL + "/user/register?firstName=" + usr.getFirst_name() + "&lastName="
                + usr.getLast_name() + "&phone="
                + usr.getPhone() + "&email="
                + usr.getEmail() + "&username="
                + usr.getUsername() + "&plainPassword="
                + usr.getPassword()+"&true";
        request.setUrl(url);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String str = new String(request.getResponseData());
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
    }
}
