package com.transaction.monitoring.monitoringservice.entity;

import javax.persistence.*;

/**
 * Created by Admin on 7/28/20.
 */

@Entity
@Table(name = "fraudsystem_account_mapping")
public class Account {

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fsaccountid")
    private Integer Id;

    @Column(name = "accountname")
    private String accountname;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "istest")
    private String isTest;

    @Column(name = "fsid")
    private String fsid;

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getAccountname() {
        return accountname;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
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

    public String getIsTest() {
        return isTest;
    }

    public void setIsTest(String isTest) {
        this.isTest = isTest;
    }

    public String getFsid() {
        return fsid;
    }

    public void setFsid(String fsid) {
        this.fsid = fsid;
    }

    public Account(){}

    public Account(String accountname, String username, String password, String isTest, String fsid) {
        this.accountname = accountname;
        this.username = username;
        this.password = password;
        this.isTest = isTest;
        this.fsid = fsid;
    }

    @Override
    public String toString() {
        return "Account{" +
                "Id=" + Id +
                ", accountname='" + accountname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isTest='" + isTest + '\'' +
                ", fsid='" + fsid + '\'' +
                '}';
    }
}
