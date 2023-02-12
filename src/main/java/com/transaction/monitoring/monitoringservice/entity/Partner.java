package com.transaction.monitoring.monitoringservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Admin on 7/24/20.
 */
@Entity
@Table(name = "partners")
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="partnerid")
    private int partnerId;

    @Column(name = "login")
    private String username;

    @Column(name = "clkey")
    private String clkey;

    @Column(name = "istransactionmonitoring")
    private String isTransactionMonitoring;

    @Column(name = "role")
    private String role;

    public Partner(){}

    public int getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(int partnerId) {
        this.partnerId = partnerId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getClkey() {
        return clkey;
    }

    public void setClkey(String clkey) {
        this.clkey = clkey;
    }

    public String getIsTransactionMonitoring() {
        return isTransactionMonitoring;
    }

    public void setIsTransactionMonitoring(String isTransactionMonitoring) {
        this.isTransactionMonitoring = isTransactionMonitoring;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Partner(String username, String clkey, String isTransactionMonitoring, String role) {
        this.username = username;
        this.clkey = clkey;
        this.isTransactionMonitoring = isTransactionMonitoring;
        this.role = role;
    }
}
