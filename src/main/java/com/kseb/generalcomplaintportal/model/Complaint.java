package com.kseb.generalcomplaintportal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int complaint_id;
    private Date complaint_date;
    private int consumer_kseb_id;
    private String complaint_phone_no;
    private String complaint_location;
    private String complaint_post_no;
    private String complaint_details;

    public Complaint(int complaint_id, int consumer_kseb_id, String complaint_phone_no, String complaint_location, String complaint_post_no, String complaint_details, Date complaint_date) {
        this.complaint_id = complaint_id;
        this.consumer_kseb_id = consumer_kseb_id;
        this.complaint_phone_no = complaint_phone_no;
        this.complaint_location = complaint_location;
        this.complaint_post_no = complaint_post_no;
        this.complaint_details = complaint_details;
        this.complaint_date = complaint_date;
    }

    public Complaint() {
    }

    public int getComplaint_id() {
        return complaint_id;
    }

    public void setComplaint_id(int complaint_id) {
        this.complaint_id = complaint_id;
    }

    public int getConsumer_kseb_id() {
        return consumer_kseb_id;
    }

    public void setConsumer_kseb_id(int consumer_kseb_id) {
        this.consumer_kseb_id = consumer_kseb_id;
    }

    public String getComplaint_phone_no() {
        return complaint_phone_no;
    }

    public void setComplaint_phone_no(String complaint_phone_no) {
        this.complaint_phone_no = complaint_phone_no;
    }

    public String getComplaint_location() {
        return complaint_location;
    }

    public void setComplaint_location(String complaint_location) {
        this.complaint_location = complaint_location;
    }

    public String getComplaint_post_no() {
        return complaint_post_no;
    }

    public void setComplaint_post_no(String complaint_post_no) {
        this.complaint_post_no = complaint_post_no;
    }

    public String getComplaint_details() {
        return complaint_details;
    }

    public void setComplaint_details(String complaint_details) {
        this.complaint_details = complaint_details;
    }

    public Date getComplaint_date() {
        return complaint_date;
    }

    public void setComplaint_date(Date complaint_date) {
        this.complaint_date = complaint_date;
    }
}
