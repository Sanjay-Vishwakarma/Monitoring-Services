package com.transaction.monitoring.monitoringservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

/**
 * Created by Admin on 7/30/20.
 */

@Entity
@Table(name = "fraud_bin_base")
public class BinBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "emailid")
    private String email;

    @Column(name = "first_six")
    private String firstSix;

    @Column(name = "last_four")
    private String lastFour;

    @Column(name = "cardtype")
    private String cardType;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "bank_location")
    private String bankLocation;

    @Column(name = "card_level")
    private String cardLevel;

    @Column(name = "card_country")
    private String cardCountry;

    @Column(name = "ishighrisk")
    private String isHighRisk;

    @JsonIgnore
    @Column(name = "fraud_transid")
    private String fraud_transid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstSix() {
        return firstSix;
    }

    public void setFirstSix(String firstSix) {
        this.firstSix = firstSix;
    }

    public String getLastFour() {
        return lastFour;
    }

    public void setLastFour(String lastFour) {
        this.lastFour = lastFour;
    }

    public String getCardType() {
        return cardType;
    }

    public void setCardType(String cardType) {
        this.cardType = cardType;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankLocation() {
        return bankLocation;
    }

    public void setBankLocation(String bankLocation) {
        this.bankLocation = bankLocation;
    }

    public String getCardLevel() {
        return cardLevel;
    }

    public void setCardLevel(String cardLevel) {
        this.cardLevel = cardLevel;
    }

    public String getCardCountry() {
        return cardCountry;
    }

    public void setCardCountry(String cardCountry) {
        this.cardCountry = cardCountry;
    }

    public String getIsHighRisk() {
        return isHighRisk;
    }

    public void setIsHighRisk(String isHighRisk) {
        this.isHighRisk = isHighRisk;
    }

    public String getFraud_transid() {
        return fraud_transid;
    }

    public void setFraud_transid(String fraud_transid) {
        this.fraud_transid = fraud_transid;
    }

    public BinBase(){}

    public BinBase(String firstName, String lastName, String email, String firstSix, String lastFour, String cardType, String bankName, String bankLocation, String cardLevel, String cardCountry, String isHighRisk, String fraud_transid) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.firstSix = firstSix;
        this.lastFour = lastFour;
        this.cardType = cardType;
        this.bankName = bankName;
        this.bankLocation = bankLocation;
        this.cardLevel = cardLevel;
        this.cardCountry = cardCountry;
        this.isHighRisk = isHighRisk;
        this.fraud_transid = fraud_transid;
    }

    @Override
    public String toString() {
        return "BinBase{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", firstSix='" + firstSix + '\'' +
                ", lastFour='" + lastFour + '\'' +
                ", cardType='" + cardType + '\'' +
                ", bankName='" + bankName + '\'' +
                ", bankLocation='" + bankLocation + '\'' +
                ", cardLevel='" + cardLevel + '\'' +
                ", cardCountry='" + cardCountry + '\'' +
                ", isHighRisk='" + isHighRisk + '\'' +
                ", fraud_transid='" + fraud_transid + '\'' +
                '}';
    }
}
