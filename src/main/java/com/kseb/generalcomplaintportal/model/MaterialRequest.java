package com.kseb.generalcomplaintportal.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "material_request")
public class MaterialRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int material_request_id;
    private int work_alloc_id;
    @Column(name = "material_item_id")
    private int materialItemId;
    private String material_request_qty;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date material_request_date;
    private String material_request_status;
    private String material_type;

    public MaterialRequest() {
    }

    public MaterialRequest(int material_request_id, int work_alloc_id, int material_item_id, String material_request_qty, Date material_request_date, String material_type) {
        this.material_request_id = material_request_id;
        this.work_alloc_id = work_alloc_id;
        this.materialItemId = material_item_id;
        this.material_request_qty = material_request_qty;
        this.material_request_date = material_request_date;
        this.material_type = material_type;
    }

    public int getMaterial_request_id() {
        return material_request_id;
    }

    public void setMaterial_request_id(int material_request_id) {
        this.material_request_id = material_request_id;
    }

    public int getWork_alloc_id() {
        return work_alloc_id;
    }

    public void setWork_alloc_id(int work_alloc_id) {
        this.work_alloc_id = work_alloc_id;
    }

    public int getMaterialItemId() {
        return materialItemId;
    }

    public void setMaterialItemId(int materialItemId) {
        this.materialItemId = materialItemId;
    }

    public String getMaterial_request_qty() {
        return material_request_qty;
    }

    public void setMaterial_request_qty(String material_request_qty) {
        this.material_request_qty = material_request_qty;
    }

    public Date getMaterial_request_date() {
        return material_request_date;
    }

    public void setMaterial_request_date(Date material_request_date) {
        this.material_request_date = material_request_date;
    }

    public String getMaterial_request_status() {
        return material_request_status;
    }

    public void setMaterial_request_status(String material_request_status) {
        this.material_request_status = material_request_status;
    }

    public String getMaterial_type() {
        return material_type;
    }

    public void setMaterial_type(String material_type) {
        this.material_type = material_type;
    }

}
