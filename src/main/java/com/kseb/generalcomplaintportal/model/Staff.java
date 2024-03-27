package com.kseb.generalcomplaintportal.model;

import jakarta.persistence.*;

@Entity
@Table(name = "staff_personal")
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int staff_id;

    @Column(name = "staff_firstname")
    private String firstName;

    @Column(name = "staff_lastname")
    private String lastName;

    @Column(name = "staff_address")
    private String address;

    @Column(name = "staff_phone")
    private String phone;

    @Column(name = "staff_email")
    private String email;

    @Column(name = "staff_desig_id")
    private int design_id;

    @Column(name = "staff_username")
    private String username;

    @Column(name = "staff_password")
    private String password;

    public Staff() {}

    public Staff(int staff_id, String firstName, String lastName, String address, String phone, String email, int design_id, String username, String password) {
        this.staff_id = staff_id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.design_id = design_id;
        this.username = username;
        this.password = password;
    }

    public int getStaff_id() {
        return staff_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDesign_id() {
        return design_id;
    }

    public void setDesign_id(int design_id) {
        this.design_id = design_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
