package com.kseb.generalcomplaintportal.model;

import com.fasterxml.jackson.databind.DatabindException;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name ="payment_bill")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int payment_bill_id;
    private int work_alloc_id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date payment_bill_date;
    private String payment_bill_amount;
    private String payment_bill_details;

    public Payment() {
    }

    public Payment(int payment_bill_id, int work_alloc_id, Date payment_bill_date, String payment_bill_amount, String payment_bill_details) {
        this.payment_bill_id = payment_bill_id;
        this.work_alloc_id = work_alloc_id;
        this.payment_bill_date = payment_bill_date;
        this.payment_bill_amount = payment_bill_amount;
        this.payment_bill_details = payment_bill_details;
    }

    public int getPayment_bill_id() {
        return payment_bill_id;
    }

    public void setPayment_bill_id(int payment_bill_id) {
        this.payment_bill_id = payment_bill_id;
    }

    public int getWork_alloc_id() {
        return work_alloc_id;
    }

    public void setWork_alloc_id(int work_alloc_id) {
        this.work_alloc_id = work_alloc_id;
    }

    public Date getPayment_bill_date() {
        return payment_bill_date;
    }

    public void setPayment_bill_date(Date payment_bill_date) {
        this.payment_bill_date = payment_bill_date;
    }

    public String getPayment_bill_amount() {
        return payment_bill_amount;
    }

    public void setPayment_bill_amount(String payment_bill_amount) {
        this.payment_bill_amount = payment_bill_amount;
    }

    public String getPayment_bill_details() {
        return payment_bill_details;
    }

    public void setPayment_bill_details(String payment_bill_details) {
        this.payment_bill_details = payment_bill_details;
    }
}
