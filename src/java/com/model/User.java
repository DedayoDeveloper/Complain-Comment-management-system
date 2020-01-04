/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.io.Serializable;

/**
 *
 * @author Supersoft
 */
public class User implements Serializable {
    
    int id;
    String institutionname;
    String products;
    String username;
    String institutionuser;
    String supersoftadmin;
    String admin;

    public String getInstitutionuser() {
        return institutionuser;
    }

    public void setInstitutionuser(String institutionuser) {
        this.institutionuser = institutionuser;
    }

    public String getSupersoftadmin() {
        return supersoftadmin;
    }

    public void setSupersoftadmin(String supersoftadmin) {
        this.supersoftadmin = supersoftadmin;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }
   

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInstitutionname() {
        return institutionname;
    }

    public void setInstitutionname(String institutionname) {
        this.institutionname = institutionname;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }
    
    
    
}
