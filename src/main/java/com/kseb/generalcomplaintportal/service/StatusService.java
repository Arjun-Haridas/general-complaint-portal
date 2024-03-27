package com.kseb.generalcomplaintportal.service;

import com.kseb.generalcomplaintportal.service.enums.Status;
import org.springframework.stereotype.Service;

@Service
public class StatusService {
    public String getStatusDescription(Status status) {
        return status.getDescription();
    }
    public String getStatusName(Status status) {
        return status.name();
    }
}
