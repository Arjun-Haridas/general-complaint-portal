package com.kseb.generalcomplaintportal.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class ComplaintRegistrationForm {
    private int complaint_id;
    private int consumer_kseb_id;
    private String complaint_phone_no;
    private String complaint_location;
    private String complaint_post_no;
    private String complaint_details;
    private int complaint_status_id;
    private Date complaint_status_updated_date;
    private  String complaint_status;
    private int complaint_status_updated_by;
    private String complaint_status_details;
    private String consumer_name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date complaint_date;

    public ComplaintRegistrationForm() {
    }

    public ComplaintRegistrationForm(int complaint_id, int consumer_kseb_id, String complaint_phone_no, String complaint_location, String complaint_post_no, String complaint_details, int complaint_status_id, Date complaint_status_updated_date, String complaint_status, int complaint_status_updated_by, String complaint_status_details, String consumer_name, Date complaint_date) {
        this.complaint_id = complaint_id;
        this.consumer_kseb_id = consumer_kseb_id;
        this.complaint_phone_no = complaint_phone_no;
        this.complaint_location = complaint_location;
        this.complaint_post_no = complaint_post_no;
        this.complaint_details = complaint_details;
        this.complaint_status_id = complaint_status_id;
        this.complaint_status_updated_date = complaint_status_updated_date;
        this.complaint_status = complaint_status;
        this.complaint_status_updated_by = complaint_status_updated_by;
        this.complaint_status_details = complaint_status_details;
        this.consumer_name = consumer_name;
        this.complaint_date = complaint_date;
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

    public int getComplaint_status_id() {
        return complaint_status_id;
    }

    public void setComplaint_status_id(int complaint_status_id) {
        this.complaint_status_id = complaint_status_id;
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

    public String getConsumer_name() {
        return consumer_name;
    }

    public void setConsumer_name(String consumer_name) {
        this.consumer_name = consumer_name;
    }

    public Date getComplaint_date() {
        return complaint_date;
    }

    public void setComplaint_date(Date complaint_date) {
        this.complaint_date = complaint_date;
    }
}
