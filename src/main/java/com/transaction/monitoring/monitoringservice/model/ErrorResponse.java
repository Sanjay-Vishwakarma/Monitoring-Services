package com.transaction.monitoring.monitoringservice.model;

import org.springframework.stereotype.Component;

/**
 * Created by Admin on 7/31/20.
 */

@Component
public class ErrorResponse {
    private String status;

    private String description;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
