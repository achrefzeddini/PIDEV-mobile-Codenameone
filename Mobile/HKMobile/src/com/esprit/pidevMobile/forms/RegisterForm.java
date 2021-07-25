/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidevMobile.forms;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.esprit.pidevMobile.models.Users;
import com.esprit.pidevMobile.services.UsersService;
import com.esprit.pidevMobile.utils.CurrentUser;

/**
 *
 * @author Testouri Mohamed
 */
public class RegisterForm extends Form {

    public RegisterForm(Resources res) {
        super("Register", BoxLayout.y());
        Users usr = new Users();
        UsersService US = new UsersService();
        CurrentUser CU = new CurrentUser();
        Label titleLB = new Label("Register");
        TextField firstnameTF = new TextField(null, "First Name");
        TextField lastnameTF = new TextField(null, "Last Name");
        TextField phoneTF = new TextField(null, "Phone", 20, TextField.PHONENUMBER);
        TextField usernameTF = new TextField(null, "Username", 20, TextField.ANY);
        TextField emailTF = new TextField(null, "E-mail", 20, TextField.EMAILADDR);
        TextField passwordTF = new TextField(null, "Password", 20, TextField.PASSWORD);
        TextField password2TF = new TextField(null, "Password", 20, TextField.PASSWORD);
        Button registerBTN = new Button("Register");
        Button loginBTN = new Button("Already have an account ?");

        registerBTN.addActionListener((evt) -> {
            if ((usernameTF.getText().length() != 0) && (firstnameTF.getText().length() != 0) && (lastnameTF.getText().length() != 0) && (phoneTF.getText().length() != 0) && (emailTF.getText().length() != 0) && (passwordTF.getText().length() != 0) && (passwordTF.getText().length() != 0)) {
                if (passwordTF.getText().equals(password2TF.getText())) {
                    if (passwordTF.getText().length() >= 8 && password2TF.getText().length() >= 8) {
                        if (phoneTF.getText().length() == 8) {
                            if (emailTF.getText().length() != 0) {
                                if (firstnameTF.getText().length() != 0) {
                                    if (lastnameTF.getText().length() != 0) {
                                        usr.setFirst_name(firstnameTF.getText());
                                        usr.setLast_name(lastnameTF.getText());
                                        usr.setPhone(Integer.parseInt(phoneTF.getText()));
                                        usr.setUsername(usernameTF.getText());
                                        usr.setEmail(emailTF.getText());
                                        usr.setPassword(password2TF.getText());
                                        usr.setEnabled(true);
                                        US.register(usr);
                                        Dialog.show("Hunt Kingdom", "Welcome " + firstnameTF.getText() + " " + lastnameTF.getText() + " !", "OK", null);
                                        CU.setIdCurrentUser(US.getUser(usernameTF.getText(), passwordTF.getText()).get(0).getId());
                                        new HomeForm(res).show();
                                    } else {
                                        Dialog.show("Alert", "Last name is invalid!", "Ok", null);
                                        lastnameTF.clear();
                                    }
                                } else {
                                    Dialog.show("Alert", "First name is invalid!", "Ok", null);
                                    firstnameTF.clear();
                                }
                            } else {
                                Dialog.show("Alert", "E-mail is invalid!", "Ok", null);
                                emailTF.clear();
                            }
                        } else {
                            Dialog.show("Alert", "Phone is invalid!", "Ok", null);
                            phoneTF.clear();
                        }
                    } else {
                        Dialog.show("Alert", "Password is too short!", "Ok", null);
                        password2TF.clear();
                        passwordTF.clear();
                    }
                } else {
                    Dialog.show("Alert", "Password dosen't match!", "Ok", null);
                    password2TF.clear();
                    passwordTF.clear();
                }
            } else {
                Dialog.show("Alert", "Please fill all the fields", "OK", null);
            }
        }
        );
        loginBTN.addActionListener((evt) -> {
            new LoginForm(res).showBack();
        });

        this.addAll(titleLB, firstnameTF, lastnameTF, phoneTF, usernameTF, emailTF, passwordTF, password2TF, registerBTN, loginBTN);
    }

}
