package com.kseb.generalcomplaintportal.service.enums;

public enum Status {
    PENDING("Complaint Pending"),
    RESOLVED("Complaint Resolved");

    private final String description;

    Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
