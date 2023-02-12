package com.transaction.monitoring.monitoringservice.model;

/**
 * Created by Admin on 7/23/20.
 */
import java.io.Serializable;

public class JwtRequest implements Serializable {

    private static final long serialVersionUID = 5926468583005150707L;

    private String partnerid;
    private String username;
    private String sKey;

    //need default constructor for JSON Parsing
    public JwtRequest()
    {

    }

    public JwtRequest(String username, String sKey) {
        this.setUsername(username);
        this.setsKey(sKey);
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid;
    }

    public String getsKey() {
        return sKey;
    }

    public void setsKey(String sKey) {
        this.sKey = sKey;
    }
}
