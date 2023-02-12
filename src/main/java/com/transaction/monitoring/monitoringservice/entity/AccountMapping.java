package com.transaction.monitoring.monitoringservice.entity;

import javax.persistence.*;

/**
 * Created by Admin on 7/24/2020.
 */

@Entity
@Table(name="partner_fsaccounts_mapping")
public class AccountMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name="partnerid")
    private Integer partnerid;

    @Column(name="fsaccountid")
    private Integer fsaccountid;

    @Column(name = "istransactionmonitoring")
    private String isTransactionMonitoring;

    @Column(name="tm_iscompliance_peps")
    private String iscompliance_peps;

    @Column(name="tm_isgeo_check")
    private String isgeo_check;

    @Column(name="tm_isidv_global_4_check")
    private String isidv_global_4_check;

    @Column(name="tm_isemail_profiling")
    private String isemail_profiling;

    @Column(name="tm_isaddress_check")
    private String isaddress_check;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(Integer partnerid) {
        this.partnerid = partnerid;
    }

    public Integer getFsaccountid() {
        return fsaccountid;
    }

    public void setFsaccountid(Integer fsaccountid) {
        this.fsaccountid = fsaccountid;
    }

    public String getIsTransactionMonitoring() {
        return isTransactionMonitoring;
    }

    public void setIsTransactionMonitoring(String isTransactionMonitoring) {
        this.isTransactionMonitoring = isTransactionMonitoring;
    }

    public AccountMapping(){}

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

    public AccountMapping(Integer partnerid, Integer fsaccountid, String isTransactionMonitoring, String iscompliance_peps, String isgeo_check, String isidv_global_4_check, String isemail_profiling, String isaddress_check) {
        this.partnerid = partnerid;
        this.fsaccountid = fsaccountid;
        this.isTransactionMonitoring = isTransactionMonitoring;
        this.iscompliance_peps = iscompliance_peps;
        this.isgeo_check = isgeo_check;
        this.isidv_global_4_check = isidv_global_4_check;
        this.isemail_profiling = isemail_profiling;
        this.isaddress_check = isaddress_check;
    }

    @Override
    public String toString() {
        return "AccountMapping{" +
                "id=" + id +
                ", partnerid=" + partnerid +
                ", fsaccountid=" + fsaccountid +
                ", isTransactionMonitoring='" + isTransactionMonitoring + '\'' +
                ", iscompliance_peps='" + iscompliance_peps + '\'' +
                ", isgeo_check='" + isgeo_check + '\'' +
                ", isidv_global_4_check='" + isidv_global_4_check + '\'' +
                ", isemail_profiling='" + isemail_profiling + '\'' +
                ", isaddress_check='" + isaddress_check + '\'' +
                '}';
    }
}
