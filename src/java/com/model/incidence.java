/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.io.Serializable;

/**
 *
 * @author Supersoft Technology
 */
public class incidence implements Serializable {

    int id;
    String sender;
    String messageBody;
    String title;
    String datelogged;
    int status;
    String response;
    String date_replied;
    String financialinstitutioncode;
    String replied_by;
    String date_closed;
    String closed_by;
    String institutionname;
    String type;
    int type_id;
    String filename;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }
    long trackingnumber;
    String products;

    public long getTrackingnumber() {
        return trackingnumber;
    }

    public void setTrackingnumber(long trackingnumber) {
        this.trackingnumber = trackingnumber;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public String getInstitutionname() {
        return institutionname;
    }

    public void setInstitutionname(String institutionname) {
        this.institutionname = institutionname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getClosed_by() {
        return closed_by;
    }

    public void setClosed_by(String closed_by) {
        this.closed_by = closed_by;
    }

    public String getReplied_by() {
        return replied_by;
    }

    public void setReplied_by(String replied_by) {
        this.replied_by = replied_by;
    }

    public String getDate_closed() {
        return date_closed;
    }

    public void setDate_closed(String date_closed) {
        this.date_closed = date_closed;
    }

    public String getFinancialinstitutioncode() {
        return financialinstitutioncode;
    }

    public void setFinancialinstitutioncode(String financialinstitutioncode) {
        this.financialinstitutioncode = financialinstitutioncode;
    }

    public String getDate_replied() {
        return date_replied;
    }

    public void setDate_replied(String date_replied) {
        this.date_replied = date_replied;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDatelogged() {
        return datelogged;
    }

    public void setDatelogged(String datelogged) {
        this.datelogged = datelogged;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }
}
