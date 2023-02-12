package com.transaction.monitoring.monitoringservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.transaction.monitoring.monitoringservice.model.*;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Transient;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Uday on 7/7/20.
 */


@Component
public class MonitoringResponse {

    @JsonIgnore
    private  String status;

    private int transId;

    @JsonIgnore
    private  String responseId;

    private String description;

    private String recommendation;

    private double score;

    @JsonIgnore
    private int confidence_level;

    BinBase binBases;

    List<TriggeredRules> triggeredRulesList;

    ErrorResponse errorResponse;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResponseId() {
        return responseId;
    }

    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getConfidence_level() {
        return confidence_level;
    }

    public void setConfidence_level(int confidence_level) {
        this.confidence_level = confidence_level;
    }

    public BinBase getBinBases() {
        return binBases;
    }

    public void setBinBases(BinBase binBases) {
        this.binBases = binBases;
    }

    public List<TriggeredRules> getTriggeredRulesList() {
        return triggeredRulesList;
    }

    public void setTriggeredRulesList(List<TriggeredRules> triggeredRulesList) {
        this.triggeredRulesList = triggeredRulesList;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public int getTransId() {
        return transId;
    }

    public void setTransId(int transId) {
        this.transId = transId;
    }
}
