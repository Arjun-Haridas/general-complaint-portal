package com.kseb.generalcomplaintportal.dto;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class MaterialRequestForm {
    private int complaint_id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date complaint_date;
    private String consumer_name;
    private String consumer_phone;
    private String complaint_location;
    private String complaint_post_no;
    private String complaint_details;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date complaint_status_updated_date;
    private String complaint_status;
    private int work_alloc_id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date work_alloc_date;
    private String staff_name;
    private int material_request_id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date material_request_date;
    private int material_item_id;
    private String material_details;
    private String material_request_status;
    private String material_issue_status;

    public MaterialRequestForm() {
    }

    public MaterialRequestForm(int complaint_id, Date complaint_date, String consumer_name, String consumer_phone, String complaint_location, String complaint_post_no, String complaint_details, Date complaint_status_updated_date, String complaint_status, int work_alloc_id, Date work_alloc_date, String staff_name, int material_request_id, Date material_request_date, int material_item_id, String material_details, String material_request_status, String material_issue_status) {
        this.complaint_id = complaint_id;
        this.complaint_date = complaint_date;
        this.consumer_name = consumer_name;
        this.consumer_phone = consumer_phone;
        this.complaint_location = complaint_location;
        this.complaint_post_no = complaint_post_no;
        this.complaint_details = complaint_details;
        this.complaint_status_updated_date = complaint_status_updated_date;
        this.complaint_status = complaint_status;
        this.work_alloc_id = work_alloc_id;
        this.work_alloc_date = work_alloc_date;
        this.staff_name = staff_name;
        this.material_request_id = material_request_id;
        this.material_request_date = material_request_date;
        this.material_item_id = material_item_id;
        this.material_details = material_details;
        this.material_request_status = material_request_status;
        this.material_issue_status = material_issue_status;
    }

    public int getComplaint_id() {
        return complaint_id;
    }

    public void setComplaint_id(int complaint_id) {
        this.complaint_id = complaint_id;
    }

    public Date getComplaint_date() {
        return complaint_date;
    }

    public void setComplaint_date(Date complaint_date) {
        this.complaint_date = complaint_date;
    }

    public String getConsumer_name() {
        return consumer_name;
    }

    public void setConsumer_name(String consumer_name) {
        this.consumer_name = consumer_name;
    }

    public String getConsumer_phone() {
        return consumer_phone;
    }

    public void setConsumer_phone(String consumer_phone) {
        this.consumer_phone = consumer_phone;
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

    public int getWork_alloc_id() {
        return work_alloc_id;
    }

    public void setWork_alloc_id(int work_alloc_id) {
        this.work_alloc_id = work_alloc_id;
    }

    public Date getWork_alloc_date() {
        return work_alloc_date;
    }

    public void setWork_alloc_date(Date work_alloc_date) {
        this.work_alloc_date = work_alloc_date;
    }

    public String getStaff_name() {
        return staff_name;
    }

    public void setStaff_name(String staff_name) {
        this.staff_name = staff_name;
    }

    public int getMaterial_request_id() {
        return material_request_id;
    }

    public void setMaterial_request_id(int material_request_id) {
        this.material_request_id = material_request_id;
    }

    public Date getMaterial_request_date() {
        return material_request_date;
    }

    public void setMaterial_request_date(Date material_request_date) {
        this.material_request_date = material_request_date;
    }

    public int getMaterial_item_id() {
        return material_item_id;
    }

    public void setMaterial_item_id(int material_item_id) {
        this.material_item_id = material_item_id;
    }

    public String getMaterial_request_status() {
        return material_request_status;
    }

    public void setMaterial_request_status(String material_request_status) {
        this.material_request_status = material_request_status;
    }

    public String getMaterial_details() {
        return material_details;
    }

    public void setMaterial_details(String material_details) {
        this.material_details = material_details;
    }

    public String getMaterial_issue_status() {
        return material_issue_status;
    }

    public void setMaterial_issue_status(String material_issue_status) {
        this.material_issue_status = material_issue_status;
    }

}
