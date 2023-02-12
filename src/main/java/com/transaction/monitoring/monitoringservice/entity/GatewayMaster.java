package com.transaction.monitoring.monitoringservice.entity;

import javax.persistence.*;

/**
 * Created by Admin on 7/28/20.
 */

@Entity
@Table(name = "fraudsystem_master")
public class GatewayMaster {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fsid")
    private Integer Id;

    @Column(name = "fsname")
    private String gatewayname;


    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getGatewayname() {
        return gatewayname;
    }

    public void setGatewayname(String gatewayname) {
        this.gatewayname = gatewayname;
    }

    public GatewayMaster(){}

    public GatewayMaster(String gatewayname) {
        this.gatewayname = gatewayname;
    }

    @Override
    public String toString() {
        return "GatewayMaster{" +
                "Id=" + Id +
                ", gatewayname='" + gatewayname + '\'' +
                '}';
    }
}
