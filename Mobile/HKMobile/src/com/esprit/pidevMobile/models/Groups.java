/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.pidevMobile.models;

import java.util.Collection;

/**
 *
 * @author Testouri Mohamed
 */
public class Groups {

    private int id;
    private String nameGroup;
    private String typeGroup;
    private Collection<Users> usersCollection;

    public Groups(String nameGroup) {
        this.nameGroup = nameGroup;
    }

    public Groups() {
    }

    public Groups(int id, String nameGroup, String typeGroup) {
        this.id = id;
        this.nameGroup = nameGroup;
        this.typeGroup = typeGroup;
    }

    public Groups(String nameGroup, int id) {
        this.id = id;
        this.nameGroup = nameGroup;
    }

    public Groups(String nameGroup, String typeGroup) {
        this.nameGroup = nameGroup;
        this.typeGroup = typeGroup;
    }

    public int getId() {
        return id;
    }

    public String getNameGroup() {
        return nameGroup;
    }

    public String getTypeGroup() {
        return typeGroup;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNameGroup(String nameGroup) {
        this.nameGroup = nameGroup;
    }

    public void setTypeGroup(String typeGroup) {
        this.typeGroup = typeGroup;
    }

    @Override
    public String toString() {
        return "Groups{" + "idGroup=" + id + ", nameGroup=" + nameGroup + ", typeGroup=" + typeGroup + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
//        final Groups other = (Groups) obj;
//        if (!Objects.equals(this.nameGroup, other.nameGroup)) {
//            return false;
//        }
//        if (!Objects.equals(this.typeGroup, other.typeGroup)) {
//            return false;
//        }
//        return true;
//    }

}
