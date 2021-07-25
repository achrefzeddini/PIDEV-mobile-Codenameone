/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidevMobile.forms;

import com.codename1.components.MultiButton;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.pidevMobile.models.Groups;
import com.esprit.pidevMobile.services.GroupsService;
import com.esprit.pidevMobile.utils.CurrentUser;
import java.util.ArrayList;

/**
 *
 * @author Testouri Mohamed
 */
public class MyGroupsForm extends Form {

    public MyGroupsForm(Resources res) {
        super("My Groups", BoxLayout.y());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, evt -> new HomeForm(res).showBack());
        getToolbar().addCommandToOverflowMenu("Logout", null, (ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new CurrentUser().disConnect();
                System.out.println(new CurrentUser().getIdCurrentUser());
                new LoginForm(res).showBack();

            }
        });
        this.getToolbar().addSearchCommand((ActionEvent e) -> {
            String searchTerm = (String) e.getSource();
            if (searchTerm.length() == 0) {
                for (Component cmp : this.getContentPane()) {
                    cmp.setHidden(false);
                    cmp.setVisible(true);
                }
                this.getContentPane().animateLayout(150);
            } else {
                searchTerm = searchTerm.toLowerCase();
                for (Component cmp : this.getContentPane()) {
                    MultiButton mb = (MultiButton) cmp;
                    String line1 = mb.getTextLine1();
                    String line2 = mb.getTextLine2();
                    String line3 = mb.getTextLine3();
                    boolean show = line1 != null && line1.toLowerCase().indexOf(searchTerm) > -1 || line2 != null && line2.toLowerCase().indexOf(searchTerm) > -1 || line3 != null && line3.toLowerCase().indexOf(searchTerm) > -1;
                    mb.setVisible(show);
                }
                this.getContentPane().animateLayout(150);
            }
        }
        );
        ArrayList<Groups> groups = new GroupsService().getMyGroups();
        Container groupList = new Container(BoxLayout.y());
        System.out.println(groups);

        for (int i = 0; i < groups.size(); i++) {
            int id = groups.get(i).getId();
            String nameGroup = groups.get(i).getNameGroup();
            String typeGroup = groups.get(i).getTypeGroup();
            MultiButton groupsMB = new MultiButton(nameGroup);
            groupsMB.setTextLine2(typeGroup);
            groupsMB.addActionListener((ActionListener) (ActionEvent evt) -> {
                //Dialog.show("Join " + nameGroup + " ?", "Are you sure ?", "Cancel", "Join");
                //Dialog.show("Join " + nameGroup + " ?", "Are you sure ?", "Cancel", "Join");
                LocalNotification n = new LocalNotification();
                n.setAlertBody("You have Left" + nameGroup);
                n.setAlertTitle("Hello");
                n.setId(""+1);
                Display.getInstance().scheduleLocalNotification(n, System.currentTimeMillis() + 10 * 100, LocalNotification.REPEAT_NONE);

                new GroupsService().leaveGroup(id);
                groupList.removeComponent(groupsMB);
                groupList.animateLayout(150);

            });
            groupList.add(groupsMB);
        }

        this.add(groupList);
    }
}
