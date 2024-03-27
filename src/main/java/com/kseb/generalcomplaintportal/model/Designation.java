package com.kseb.generalcomplaintportal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Designation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int design_id;
    private int dept_id;
    private String designation;

    public Designation() {
    }

    public Designation(int design_id, int dept_id, String designation) {
        this.design_id = design_id;
        this.dept_id = dept_id;
        this.designation = designation;
    }

    public int getDesign_id() {
        return design_id;
    }

    public int getDept_id() {
        return dept_id;
    }

    public void setDept_id(int dept_id) {
        this.dept_id = dept_id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
