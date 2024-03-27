package com.kseb.generalcomplaintportal.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "complaint_status")
public class ComplaintStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int complaint_status_id;
    private int complaint_id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date complaint_status_updated_date;
    private  String complaint_status;
    private int complaint_status_updated_by;
    private String complaint_status_details;
    private String selectedStatus;

    public ComplaintStatus(int complaint_status_id, int complaint_id, Date complaint_status_updated_date, String complaint_status, int complaint_status_updated_by, String complaint_status_details, String selectedStatus) {
        this.complaint_status_id = complaint_status_id;
        this.complaint_id = complaint_id;
        this.complaint_status_updated_date = complaint_status_updated_date;
        this.complaint_status = complaint_status;
        this.complaint_status_updated_by = complaint_status_updated_by;
        this.complaint_status_details = complaint_status_details;
        this.selectedStatus = selectedStatus;
    }

    public ComplaintStatus() {
    }

    public int getComplaint_status_id() {
        return complaint_status_id;
    }

    public void setComplaint_status_id(int complaint_status_id) {
        this.complaint_status_id = complaint_status_id;
    }

    public int getComplaint_id() {
        return complaint_id;
    }

    public void setComplaint_id(int complaint_id) {
        this.complaint_id = complaint_id;
    }

    public Date getComplaint_status_updated_date() {
        return complaint_status_updated_date;
    }

    public void setComplaint_status_updated_date(Date complaint_status_updated_date) {
        this.complaint_status_updated_date = complaint_status_updated_date;
    }

    public String getComplaint_status() {
        return complaint_status;
    }

    public void setComplaint_status(String complaint_status) {
        this.complaint_status = complaint_status;
    }

    public int getComplaint_status_updated_by() {
        return complaint_status_updated_by;
    }

    public void setComplaint_status_updated_by(int complaint_status_updated_by) {
        this.complaint_status_updated_by = complaint_status_updated_by;
    }

    public String getComplaint_status_details() {
        return complaint_status_details;
    }

    public void setComplaint_status_details(String complaint_status_details) {
        this.complaint_status_details = complaint_status_details;
    }

    public String getSelectedStatus() {
        return selectedStatus;
    }

    public void setSelectedStatus(String selectedStatus) {
        this.selectedStatus = selectedStatus;
    }
}
