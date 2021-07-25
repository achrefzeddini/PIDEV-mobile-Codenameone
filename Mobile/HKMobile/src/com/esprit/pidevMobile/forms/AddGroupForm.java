/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidevMobile.forms;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.pidevMobile.models.Groups;
import com.esprit.pidevMobile.services.GroupsService;

/**
 *
 * @author Testouri Mohamed
 */
public class AddGroupForm extends Form {
    public AddGroupForm(Resources res){
        super("Add group", BoxLayout.y());
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, evt -> new GroupsForm(res).showBack());
    TextField namegroupTF = new TextField(null, "Name Group");
        TextField typegroupTF = new TextField(null, "Type Group");
        Button addgroupBTN = new Button("Add");
        
        addgroupBTN.addActionListener((evt) -> {
            if ((namegroupTF.getText().length() == 0) || (typegroupTF.getText().length() == 0)) {
                Dialog.show("Alert", "Please fill all the fields", "OK", null);
            } else {
                try {
                    Groups g = new Groups(namegroupTF.getText(), typegroupTF.getText());
                    if (new GroupsService().addGroup(g)) {
                        Dialog.show("SUCCESS", namegroupTF.getText()+"Added", "OK", null);
                        new GroupsForm(res).showBack();
                    } else {
                        Dialog.show("ERROR", "Server error", "OK", null);
                    }
                } catch (NumberFormatException e) {
                    Dialog.show("ERROR", "Status must be a number", "OK", null);
                }

            }
        });

        this.addAll(namegroupTF, typegroupTF, addgroupBTN);
    }
}
