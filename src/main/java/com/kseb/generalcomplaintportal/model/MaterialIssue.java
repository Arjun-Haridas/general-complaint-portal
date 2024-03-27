package com.kseb.generalcomplaintportal.model;


import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "material_issue")
public class MaterialIssue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int material_issue_id;
    private  int material_request_id;
    private String material_issued_qty;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date material_issue_status_updated_date;
    private String material_issued_status;
    private String material_issued_status_details;

    public MaterialIssue() {
    }

    public MaterialIssue(int material_issue_id, int material_request_id, String material_issued_qty, Date material_issue_status_updated_date, String material_issued_status, String material_issued_status_details) {
        this.material_issue_id = material_issue_id;
        this.material_request_id = material_request_id;
        this.material_issued_qty = material_issued_qty;
        this.material_issue_status_updated_date = material_issue_status_updated_date;
        this.material_issued_status = material_issued_status;
        this.material_issued_status_details = material_issued_status_details;
    }

    public int getMaterial_issue_id() {
        return material_issue_id;
    }

    public void setMaterial_issue_id(int material_issue_id) {
        this.material_issue_id = material_issue_id;
    }

    public int getMaterial_request_id() {
        return material_request_id;
    }

    public void setMaterial_request_id(int material_request_id) {
        this.material_request_id = material_request_id;
    }

    public String getMaterial_issued_qty() {
        return material_issued_qty;
    }

    public void setMaterial_issued_qty(String material_issued_qty) {
        this.material_issued_qty = material_issued_qty;
    }

    public Date getMaterial_issue_status_updated_date() {
        return material_issue_status_updated_date;
    }

    public void setMaterial_issue_status_updated_date(Date material_issue_status_updated_date) {
        this.material_issue_status_updated_date = material_issue_status_updated_date;
    }

    public String getMaterial_issued_status() {
        return material_issued_status;
    }

    public void setMaterial_issued_status(String material_issued_status) {
        this.material_issued_status = material_issued_status;
    }

    public String getMaterial_issued_status_details() {
        return material_issued_status_details;
    }

    public void setMaterial_issued_status_details(String material_issued_status_details) {
        this.material_issued_status_details = material_issued_status_details;
    }
}
