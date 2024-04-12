package com.kseb.generalcomplaintportal.model;


import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "work_allocation")
public class WorkAllocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int work_alloc_id;
    private int complaint_id;
    private int staff_id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date work_alloc_date;
    private String staffName;
    private String complaint;
    private boolean allowEdit;

    public WorkAllocation() {
    }

    public WorkAllocation(int work_alloc_id, int complaint_id, int staff_id, Date work_alloc_date, String staffName, String complaint) {
        this.work_alloc_id = work_alloc_id;
        this.complaint_id = complaint_id;
        this.staff_id = staff_id;
        this.work_alloc_date = work_alloc_date;
        this.staffName = staffName;
        this.complaint = complaint;
    }

    public int getWork_alloc_id() {
        return work_alloc_id;
    }

    public void setWork_alloc_id(int work_alloc_id) {
        this.work_alloc_id = work_alloc_id;
    }

    public int getComplaint_id() {
        return complaint_id;
    }

    public void setComplaint_id(int complaint_id) {
        this.complaint_id = complaint_id;
    }

    public int getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(int staff_id) {
        this.staff_id = staff_id;
    }

    public Date getWork_alloc_date() {
        return work_alloc_date;
    }

    public void setWork_alloc_date(Date work_alloc_date) {
        this.work_alloc_date = work_alloc_date;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getComplaint() {
        return complaint;
    }

    public void setComplaint(String complaint) {
        this.complaint = complaint;
    }

    public boolean isAllowEdit() {
        return allowEdit;
    }

    public void setAllowEdit(boolean allowEdit) {
        this.allowEdit = allowEdit;
    }
}
