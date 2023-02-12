package com.transaction.monitoring.monitoringservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Admin on 8/12/2020.
 */

@Entity
@Table(name="fourstopriskrule")
public class RiskRule{

    @Id
    @Column(name="id")
    int id;

    @Column(name="ruletype")
    String ruleType;

    @Column(name="rulename")
    String ruleName;

    @Column(name="ruledescription")
    String ruleDescription;

    @Column(name="score")
    String score;

    @Column(name="partnerid")
    String partnerId;

    @Column(name="ruleid")
    String ruleId;

    public RiskRule(){}

    public RiskRule(String ruleType, String ruleName, String ruleDescription, String score, String partnerId, String ruleId) {
        this.ruleType = ruleType;
        this.ruleName = ruleName;
        this.ruleDescription = ruleDescription;
        this.score = score;
        this.partnerId = partnerId;
        this.ruleId = ruleId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRuleType() {
        return ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }

    public String getRuleName() {
        return ruleName;
    }

    public void setRuleName(String ruleName) {
        this.ruleName = ruleName;
    }

    public String getRuleDescription() {
        return ruleDescription;
    }

    public void setRuleDescription(String ruleDescription) {
        this.ruleDescription = ruleDescription;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getRuleId() {
        return ruleId;
    }

    public void setRuleId(String ruleId) {
        this.ruleId = ruleId;
    }

    @Override
    public String toString() {
        return "RiskRule{" +
                "id=" + id +
                ", ruleType='" + ruleType + '\'' +
                ", ruleName='" + ruleName + '\'' +
                ", ruleDescription='" + ruleDescription + '\'' +
                ", score='" + score + '\'' +
                ", partnerId='" + partnerId + '\'' +
                ", ruleId='" + ruleId + '\'' +
                '}';
    }
}
