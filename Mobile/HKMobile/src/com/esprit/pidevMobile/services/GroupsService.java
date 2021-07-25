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
import com.esprit.pidevMobile.models.Groups;
import com.esprit.pidevMobile.utils.CurrentUser;
import com.esprit.pidevMobile.utils.DataSource;
import com.esprit.pidevMobile.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Testouri Mohamed
 */
public class GroupsService {

    private ConnectionRequest request;
    private boolean responseResult;
    private ArrayList<Groups> groups;

    CurrentUser CU = new CurrentUser();

    public GroupsService() {
        request = DataSource.getInstance().getRequest();
    }

    public boolean addGroup(Groups groups) {
        String url = Statics.BASE_URL + "/groups/new?nameGroup=" + groups.getNameGroup() + "&typeGroup=" + groups.getTypeGroup();

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

    public ArrayList<Groups> getAllGroups() {
        String url = Statics.BASE_URL + "/groups/all:" + CU.getIdCurrentUser();

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                groups = parseGroups(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return groups;
    }

    public ArrayList<Groups> getMyGroups() {
        String url = Statics.BASE_URL + "/groups/myall:" + CU.getIdCurrentUser();

        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                groups = parseGroups(new String(request.getResponseData()));
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);

        return groups;
    }

    public ArrayList<Groups> parseGroups(String jsonText) {
        try {
            groups = new ArrayList<Groups>();
            JSONParser jp = new JSONParser();
            Map<String, Object> groupsListJson = jp.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) groupsListJson.get("root");
            for (Map<String, Object> obj : list) {
                int id = (int) Float.parseFloat(obj.get("id").toString());
                String nameGroup = obj.get("nameGroup").toString();
                String typeGroup = obj.get("typeGroup").toString();
                groups.add(new Groups(id, nameGroup, typeGroup));
            }
        } catch (IOException ex) {
        }

        return groups;
    }

    public boolean joinGroup(int idG) {
        String url = Statics.BASE_URL + "/groups/join:" + CU.getIdCurrentUser() + ":" + idG;

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

    public boolean leaveGroup(int idG) {
        String url = Statics.BASE_URL + "/groups/leave:" + CU.getIdCurrentUser() + ":" + idG;

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
}
