/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidevMobile.forms;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.pidevMobile.services.UsersService;
import com.esprit.pidevMobile.utils.CurrentUser;


/**
 *
 * @author Testouri Mohamed
 */
public class LoginForm extends Form {

    UsersService US = new UsersService();
    CurrentUser CU = new CurrentUser();

    public LoginForm(Resources res ) {
        super("Login", BoxLayout.y());
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());

        Image img = res.getImage("e7e5e5.png");

        if (img.getHeight() < size) {

            img = img.scaledHeight(size);

        }
        

        img = img.scaledHeight(400);
        img = img.scaledWidth(800);

        ImageViewer ATT = new ImageViewer(img);
        TextField emailTF = new TextField(null, "E-mail", 20, TextField.ANY);
        TextField passwordTF = new TextField(null, "Password", 20, TextField.PASSWORD);
        Button loginBTN = new Button("Login");
        Button registerBTN = new Button("Create an account ?");
        
//
        loginBTN.addActionListener((evt) -> {

            if ((emailTF.getText().length() != 0) && (passwordTF.getText().length() != 0)) {
                if (emailTF.getText().length() != 0) {
                    if (passwordTF.getText().length() >= 0) {
                        boolean check = US.checkLogin(emailTF.getText(), passwordTF.getText());
                        if (check) {
                            CU.setIdCurrentUser(US.getUser(emailTF.getText(), passwordTF.getText()).get(0).getId());
                            CU.setCurrentUser(US.getUser(emailTF.getText(), passwordTF.getText()).get(0));
                            Dialog.show("Hunt Kingdom", "Welcome Back " + CU.getCurrentUser().getUsername()+ " !", "Thank you !", null);
                            new HomeForm(res).show();
                        }
                    } else {
                        Dialog.show("Alert", "Password is invalid!", "Ok", null);
                    }
                } else {
                    Dialog.show("Alert", "E-mail is invalid!", "Ok", null);
                }
            } else {
                Dialog.show("Alert", "Please fill all the fields", "OK", null);
            }
        });
        registerBTN.addActionListener((evt) -> new RegisterForm(res).show());

        this.addAll(ATT,emailTF, passwordTF, loginBTN, registerBTN);
    }

}
