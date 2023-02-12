package com.transaction.monitoring.monitoringservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

/**
 * Created by Admin on 7/30/20.
 */

@Entity
@Table(name = "fraudtransaction_rules_triggered")
public class TriggeredRules {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "rulename")
    private String rulename;

    @JsonIgnore
    @Column(name = "rulescore")
    private String rulescore;

    @JsonIgnore
    @Column(name = "fraud_transid")
    private String fraud_transid;

    @JsonProperty("rulescore")
    @Column(name="dbscore")
    int dbscore;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRulename() {
        return rulename;
    }

    public void setRulename(String rulename) {
        this.rulename = rulename;
    }

    public String getRulescore() {
        return rulescore;
    }

    public void setRulescore(String rulescore) {
        this.rulescore = rulescore;
    }

    public String getFraud_transid() {
        return fraud_transid;
    }

    public void setFraud_transid(String fraud_transid) {
        this.fraud_transid = fraud_transid;
    }

    public int getDbscore() {
        return dbscore;
    }

    public void setDbscore(int dbscore) {
        this.dbscore = dbscore;
    }

    public TriggeredRules(){}

    public TriggeredRules(String rulename, String rulescore, String fraud_transid, int dbscore) {
        this.rulename = rulename;
        this.rulescore = rulescore;
        this.fraud_transid = fraud_transid;
        this.dbscore = dbscore;
    }

    @Override
    public String toString() {
        return "TriggeredRules{" +
                "id=" + id +
                ", rulename='" + rulename + '\'' +
                ", rulescore='" + rulescore + '\'' +
                ", fraud_transid='" + fraud_transid + '\'' +
                ", dbscore=" + dbscore +
                '}';
    }
}
