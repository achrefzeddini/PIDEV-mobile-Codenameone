/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidevMobile.models;

import java.util.Collection;
import java.util.Date;

/**
 *
 * @author Testouri Mohamed
 */
public class Users {

    private int id;
    public String username;
    private String username_canonical;
    private String email;
    private String email_canonical;
    private boolean enabled;
    private String salt;
    private String password;
    private String confirmation_token;
    private Date password_requested_at;
    private String roles;
    private String first_name;
    private String last_name;
    private int phone;
    private Date lastLogin;
    private Collection<Groups> groupsCollection;

    public Users(int id, String firstname, String lastname, int phone, String email, String username) {
        this.id = id;
        this.first_name = firstname;
        this.last_name = lastname;
        this.phone = phone;
        this.email = email;
        this.username = username;
    }

    public Users() {
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        Users other = (Users) obj;
//        if (!Objects.equals(this.username, other.username)) {
//            return false;
//        }
//        if (!Objects.equals(this.username_canonical, other.username_canonical)) {
//            return false;
//        }
//        if (!Objects.equals(this.email, other.email)) {
//            return false;
//        }
//        if (!Objects.equals(this.email_canonical, other.email_canonical)) {
//            return false;
//        }
//        if (!Objects.equals(this.salt, other.salt)) {
//            return false;
//        }
//        if (!Objects.equals(this.password, other.password)) {
//            return false;
//        }
//        if (!Objects.equals(this.confirmation_token, other.confirmation_token)) {
//            return false;
//        }
//        if (!Objects.equals(this.roles, other.roles)) {
//            return false;
//        }
//        if (!Objects.equals(this.first_name, other.first_name)) {
//            return false;
//        }
//        if (!Objects.equals(this.last_name, other.last_name)) {
//            return false;
//        }
//        return true;
//    }
    @Override
    public String toString() {
        return "Users{" + "id=" + id + ", username=" + username + ", username_canonical=" + username_canonical + ", email=" + email + ", email_canonical=" + email_canonical + ", enabled=" + enabled + ", salt=" + salt + ", password=" + password + ", confirmation_token=" + confirmation_token + ", password_requested_at=" + password_requested_at + ", roles=" + roles + ", first_name=" + first_name + ", last_name=" + last_name + ", phone=" + phone + ", lastLogin=" + lastLogin + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername_canonical() {
        return username_canonical;
    }

    public void setUsername_canonical(String username_canonical) {
        this.username_canonical = username_canonical;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail_canonical() {
        return email_canonical;
    }

    public void setEmail_canonical(String email_canonical) {
        this.email_canonical = email_canonical;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmation_token() {
        return confirmation_token;
    }

    public void setConfirmation_token(String confirmation_token) {
        this.confirmation_token = confirmation_token;
    }

    public Date getPassword_requested_at() {
        return password_requested_at;
    }

    public void setPassword_requested_at(Date password_requested_at) {
        this.password_requested_at = password_requested_at;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

}
