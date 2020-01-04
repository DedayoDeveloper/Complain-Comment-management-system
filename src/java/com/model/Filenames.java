/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Supersoft Technology
 */
public class Filenames implements Serializable {
    private int id;

    private String location;
    private int approval_status;
    private String approved_by;
    private String file_name;
    private String uploaded_by;
    private String date_uploaded;
    private String totalAmount;
    private String fileStatus;
    private String amount;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getApproval_status() {
        return approval_status;
    }

    public void setApproval_status(int approval_status) {
        this.approval_status = approval_status;
    }

    public String getApproved_by() {
        return approved_by;
    }

    public void setApproved_by(String approved_by) {
        this.approved_by = approved_by;
    }
    
    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUploaded_by() {
        return uploaded_by;
    }

    public void setUploaded_by(String uploaded_by) {
        this.uploaded_by = uploaded_by;
    }

    public String getDate_uploaded() {
        
        return date_uploaded;
    }

    public void setDate_uploaded(String date_uploaded) {
        
        this.date_uploaded = date_uploaded;
    }

    public String getFileStatus() {
        return fileStatus;
    }

    public void setFileStatus(String fileStatus) {
        this.fileStatus = fileStatus;
    }
}
