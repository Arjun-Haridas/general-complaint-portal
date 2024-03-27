package com.kseb.generalcomplaintportal.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table( name="material_request_status")
public class MaterialRequestStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int material_request_status_id;
    @Column(name = "material_request_id")
    private int materialRequestId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date material_request_status_updated_date;
    private String material_request_status;
    private String material_request_status_updated_by;
    private String material_request_status_details;

    public MaterialRequestStatus() {
    }

    public MaterialRequestStatus(int material_request_status_id, int material_request_id, Date material_request_status_updated_date, String material_request_status, String material_request_status_updated_by, String material_request_status_details) {
        this.material_request_status_id = material_request_status_id;
        this.materialRequestId = material_request_id;
        this.material_request_status_updated_date = material_request_status_updated_date;
        this.material_request_status = material_request_status;
        this.material_request_status_updated_by = material_request_status_updated_by;
        this.material_request_status_details = material_request_status_details;
    }

    public int getMaterial_request_status_id() {
        return material_request_status_id;
    }

    public void setMaterial_request_status_id(int material_request_status_id) {
        this.material_request_status_id = material_request_status_id;
    }

    public int getMaterialRequestId() {
        return materialRequestId;
    }

    public void setMaterialRequestId(int materialRequestId) {
        this.materialRequestId = materialRequestId;
    }

    public Date getMaterial_request_status_updated_date() {
        return material_request_status_updated_date;
    }

    public void setMaterial_request_status_updated_date(Date material_request_status_updated_date) {
        this.material_request_status_updated_date = material_request_status_updated_date;
    }

    public String getMaterial_request_status() {
        return material_request_status;
    }

    public void setMaterial_request_status(String material_request_status) {
        this.material_request_status = material_request_status;
    }

    public String getMaterial_request_status_updated_by() {
        return material_request_status_updated_by;
    }

    public void setMaterial_request_status_updated_by(String material_request_status_updated_by) {
        this.material_request_status_updated_by = material_request_status_updated_by;
    }

    public String getMaterial_request_status_details() {
        return material_request_status_details;
    }

    public void setMaterial_request_status_details(String material_request_status_details) {
        this.material_request_status_details = material_request_status_details;
    }
}
