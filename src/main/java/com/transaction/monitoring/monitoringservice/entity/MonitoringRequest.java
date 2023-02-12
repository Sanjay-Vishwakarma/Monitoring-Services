package com.transaction.monitoring.monitoringservice.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@Entity
@Table(name="fraud_transaction")
public class MonitoringRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="fraud_transaction_id")
    Integer fraud_transaction_id;

    @Column(name="memberid")
    Integer memberid;

    @NotNull
    @Size(min=3,max=50,message = "Please enter valid FirstName")
    @Column(name="firstname")
    String firstname;

    @NotNull
    @Size(min=3,max=50,message = "Please enter valid LastName")
    @Column(name="lastname")
    String lastname;

    @NotNull
    @Email()
    @Size(max=50,message = "Please enter valid Email Address")
    @Column(name="emailaddr")
    String emailaddr;

    @NotNull(message = "Please enter valid Address.")
    @Column(name="address1")
    String address1;

    @NotNull(message = "Please enter valid City")
    @Column(name="city")
    String city;

    @Column(name="state")
    String state;

    @Size(min = 2,max = 3,message = "Please enter valid Country Code")
    @Column(name="countrycode")
    String countrycode;

    @NotNull
    @Size(max = 10,message = "Please enter valid Zipcode")
    @Column(name="zip")
    String zip;

    @Column(name="phone")
    String phone;

    @NotNull
    @Size(message = "Please enter valid IP Address")
    @Column(name="ipaddrs")
    String ipaddrs;

    @NotNull
    @Size(max = 6,message = "Please enter valid FirstSix Card Number")
    @Column(name="firstsix")
    String firstsix;

    @NotNull
    @Size(max = 4,message = "Please enter valid LastFour Card Number")
    @Column(name="lastfour")
    String lastfour;

    @Column(name="dailycardminlimit")
    Integer dailycardminlimit;

    @Column(name="dailycardlimit")
    Integer dailycardlimit;

    @Column(name="weeklycardlimit")
    Integer weeklycardlimit;

    @Column(name="monthlycardlimit")
    Integer monthlycardlimit;

    @NotNull
    @Size(max = 2,message = "Please enter valid Paymenttype")
    @Column(name="paymenttype")
    String paymenttype;

    @Column(name="partnerid")
    Integer partnerid;

    @Column(name="website")
    String website;

    @Column(name="username")
    String username;

    @Column(name="usernumber")
    String usernumber;

    @Column(name="currency")
    String currency;

    @Column(name="amount")
    String amount;

    @Column(name="status")
    String status;

    @Column(name = "responseid")
    String responseId;

    @Column(name = "description")
    String description;

    @Column(name = "recommendation")
    String recommendation;

    @Column(name = "score")
    double score;

    @Column(name = "confidence_level")
    int confidence_level;

    @Column(name="iscompliance_peps")
    private String iscompliance_peps;

    @Column(name="isgeo_check")
    private String isgeo_check;

    @Column(name="isidv_global_4_check")
    private String isidv_global_4_check;

    @Column(name="isemail_profiling")
    private String isemail_profiling;

    @Column(name="isaddress_check")
    private String isaddress_check;

    @Column(name = "fsid")
    private String fsid;

    @Column(name = "fraud_trans_status")
    private String fraud_trans_status;

    @Column(name = "dtstamp")
    private int dtstamp;

    @Column(name = "modified_by")
    private  String modified_by;

    @Column(name = "role")
    private  String role;

    @Column(name = "request_header")
    private String request_header;

    @Column(name = "header_ip")
    private  String header_ip;

    @JsonInclude()
    @Transient
    String time;

    @Column(name = "dob")
    String dob;

    @JsonInclude()
    @Transient
    String reason;

    @JsonInclude()
    @Transient
    private Account account;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public MonitoringRequest(){}

    public Integer getFraud_transaction_id() {
        return fraud_transaction_id;
    }

    public void setFraud_transaction_id(Integer fraud_transaction_id) {
        this.fraud_transaction_id = fraud_transaction_id;
    }

    public Integer getMemberid() {
        return memberid;
    }

    public void setMemberid(Integer memberid) {
        this.memberid = memberid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getEmailaddr() {
        return emailaddr;
    }

    public void setEmailaddr(String emailaddr) {
        this.emailaddr = emailaddr;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountrycode() {
        return countrycode;
    }

    public void setCountrycode(String countrycode) {
        this.countrycode = countrycode;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIpaddrs() {
        return ipaddrs;
    }

    public void setIpaddrs(String ipaddrs) {
        this.ipaddrs = ipaddrs;
    }

    public String getFirstsix() {
        return firstsix;
    }

    public void setFirstsix(String firstsix) {
        this.firstsix = firstsix;
    }

    public String getLastfour() {
        return lastfour;
    }

    public void setLastfour(String lastfour) {
        this.lastfour = lastfour;
    }

    public Integer getDailycardminlimit() {
        return dailycardminlimit;
    }

    public void setDailycardminlimit(Integer dailycardminlimit) {
        this.dailycardminlimit = dailycardminlimit;
    }

    public Integer getDailycardlimit() {
        return dailycardlimit;
    }

    public void setDailycardlimit(Integer dailycardlimit) {
        this.dailycardlimit = dailycardlimit;
    }

    public Integer getWeeklycardlimit() {
        return weeklycardlimit;
    }

    public void setWeeklycardlimit(Integer weeklycardlimit) {
        this.weeklycardlimit = weeklycardlimit;
    }

    public Integer getMonthlycardlimit() {
        return monthlycardlimit;
    }

    public void setMonthlycardlimit(Integer monthlycardlimit) {
        this.monthlycardlimit = monthlycardlimit;
    }

    public String getPaymenttype() {
        return paymenttype;
    }

    public void setPaymenttype(String paymenttype) {
        this.paymenttype = paymenttype;
    }

    public Integer getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(Integer partnerid) {
        this.partnerid = partnerid;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsernumber() {
        return usernumber;
    }

    public void setUsernumber(String usernumber) {
        this.usernumber = usernumber;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

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

    public String getIscompliance_peps() {
        return iscompliance_peps;
    }

    public void setIscompliance_peps(String iscompliance_peps) {
        this.iscompliance_peps = iscompliance_peps;
    }

    public String getIsgeo_check() {
        return isgeo_check;
    }

    public void setIsgeo_check(String isgeo_check) {
        this.isgeo_check = isgeo_check;
    }

    public String getIsidv_global_4_check() {
        return isidv_global_4_check;
    }

    public void setIsidv_global_4_check(String isidv_global_4_check) {
        this.isidv_global_4_check = isidv_global_4_check;
    }

    public String getIsemail_profiling() {
        return isemail_profiling;
    }

    public void setIsemail_profiling(String isemail_profiling) {
        this.isemail_profiling = isemail_profiling;
    }

    public String getIsaddress_check() {
        return isaddress_check;
    }

    public void setIsaddress_check(String isaddress_check) {
        this.isaddress_check = isaddress_check;
    }

    public String getFsid() {
        return fsid;
    }

    public void setFsid(String fsid) {
        this.fsid = fsid;
    }

    public String getFraud_trans_status() {
        return fraud_trans_status;
    }

    public void setFraud_trans_status(String fraud_trans_status) {
        this.fraud_trans_status = fraud_trans_status;
    }

    public int getDtstamp() {
        return dtstamp;
    }

    public void setDtstamp(int dtstamp) {
        this.dtstamp = dtstamp;
    }

    public String getModified_by() {
        return modified_by;
    }

    public void setModified_by(String modified_by) {
        this.modified_by = modified_by;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRequest_header() {
        return request_header;
    }

    public void setRequest_header(String request_header) {
        this.request_header = request_header;
    }

    public String getHeader_ip() {
        return header_ip;
    }

    public void setHeader_ip(String header_ip) {
        this.header_ip = header_ip;
    }

    public MonitoringRequest(Integer memberid, @NotNull @Size(min = 3, max = 50, message = "Please enter valid FirstName") String firstname, @NotNull @Size(min = 3, max = 50, message = "Please enter valid LastName") String lastname, @NotNull @Email() @Size(max = 50, message = "Please enter valid Email Address") String emailaddr, @NotNull(message = "Please enter valid Address.") String address1, @NotNull(message = "Please enter valid City") String city, String state, @Size(min = 2, max = 3, message = "Please enter valid Country Code") String countrycode, @NotNull @Size(max = 10, message = "Please enter valid Zipcode") String zip, String phone, @NotNull @Size(message = "Please enter valid IP Address") String ipaddrs, @NotNull @Size(max = 6, message = "Please enter valid FirstSix Card Number") String firstsix, @NotNull @Size(max = 4, message = "Please enter valid LastFour Card Number") String lastfour, Integer dailycardminlimit, Integer dailycardlimit, Integer weeklycardlimit, Integer monthlycardlimit, @NotNull @Size(max = 2, message = "Please enter valid Paymenttype") String paymenttype, Integer partnerid, String website, String username, String usernumber, String currency, String amount, String status, String responseId, String description, String recommendation, double score, int confidence_level, String iscompliance_peps, String isgeo_check, String isidv_global_4_check, String isemail_profiling, String isaddress_check, String fsid, String fraud_trans_status, int dtstamp, String modified_by, String role, String request_header, String header_ip, String time, String dob, String reason, Account account) {
        this.memberid = memberid;
        this.firstname = firstname;
        this.lastname = lastname;
        this.emailaddr = emailaddr;
        this.address1 = address1;
        this.city = city;
        this.state = state;
        this.countrycode = countrycode;
        this.zip = zip;
        this.phone = phone;
        this.ipaddrs = ipaddrs;
        this.firstsix = firstsix;
        this.lastfour = lastfour;
        this.dailycardminlimit = dailycardminlimit;
        this.dailycardlimit = dailycardlimit;
        this.weeklycardlimit = weeklycardlimit;
        this.monthlycardlimit = monthlycardlimit;
        this.paymenttype = paymenttype;
        this.partnerid = partnerid;
        this.website = website;
        this.username = username;
        this.usernumber = usernumber;
        this.currency = currency;
        this.amount = amount;
        this.status = status;
        this.responseId = responseId;
        this.description = description;
        this.recommendation = recommendation;
        this.score = score;
        this.confidence_level = confidence_level;
        this.iscompliance_peps = iscompliance_peps;
        this.isgeo_check = isgeo_check;
        this.isidv_global_4_check = isidv_global_4_check;
        this.isemail_profiling = isemail_profiling;
        this.isaddress_check = isaddress_check;
        this.fsid = fsid;
        this.fraud_trans_status = fraud_trans_status;
        this.dtstamp = dtstamp;
        this.modified_by = modified_by;
        this.role = role;
        this.request_header = request_header;
        this.header_ip = header_ip;
        this.time = time;
        this.dob = dob;
        this.reason = reason;
        this.account = account;
    }

    @Override
    public String toString() {
        return "MonitoringRequest{" +
                "fraud_transaction_id=" + fraud_transaction_id +
                ", memberid=" + memberid +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", emailaddr='" + emailaddr + '\'' +
                ", address1='" + address1 + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", countrycode='" + countrycode + '\'' +
                ", zip='" + zip + '\'' +
                ", phone='" + phone + '\'' +
                ", ipaddrs='" + ipaddrs + '\'' +
                ", firstsix='" + firstsix + '\'' +
                ", lastfour='" + lastfour + '\'' +
                ", dailycardminlimit=" + dailycardminlimit +
                ", dailycardlimit=" + dailycardlimit +
                ", weeklycardlimit=" + weeklycardlimit +
                ", monthlycardlimit=" + monthlycardlimit +
                ", paymenttype='" + paymenttype + '\'' +
                ", partnerid=" + partnerid +
                ", website='" + website + '\'' +
                ", username='" + username + '\'' +
                ", usernumber='" + usernumber + '\'' +
                ", currency='" + currency + '\'' +
                ", amount='" + amount + '\'' +
                ", status='" + status + '\'' +
                ", responseId='" + responseId + '\'' +
                ", description='" + description + '\'' +
                ", recommendation='" + recommendation + '\'' +
                ", score=" + score +
                ", confidence_level=" + confidence_level +
                ", iscompliance_peps='" + iscompliance_peps + '\'' +
                ", isgeo_check='" + isgeo_check + '\'' +
                ", isidv_global_4_check='" + isidv_global_4_check + '\'' +
                ", isemail_profiling='" + isemail_profiling + '\'' +
                ", isaddress_check='" + isaddress_check + '\'' +
                ", fsid='" + fsid + '\'' +
                ", fraud_trans_status='" + fraud_trans_status + '\'' +
                ", dtstamp=" + dtstamp +
                ", modified_by='" + modified_by + '\'' +
                ", role='" + role + '\'' +
                ", request_header='" + request_header + '\'' +
                ", header_ip='" + header_ip + '\'' +
                ", time='" + time + '\'' +
                ", dob='" + dob + '\'' +
                ", reason='" + reason + '\'' +
                ", account=" + account +
                '}';
    }
}
