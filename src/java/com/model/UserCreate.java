/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.io.Serializable;

/**
 *
 * @author oreoluwa
 */
public class UserCreate implements Serializable{
    
    
    
    String username;
    String institutionname;
    int firsttimelogin;
    String phonenumber;
    int id;

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

    public String getInstitutionname() {
        return institutionname;
    }

    public void setInstitutionname(String institutionname) {
        this.institutionname = institutionname;
    }

    public int getFirsttimelogin() {
        return firsttimelogin;
    }

    public void setFirsttimelogin(int firsttimelogin) {
        this.firsttimelogin = firsttimelogin;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
    
    
    
    
}
