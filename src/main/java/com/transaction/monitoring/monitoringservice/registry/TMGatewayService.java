package com.transaction.monitoring.monitoringservice.registry;


public interface TMGatewayService {
    public  AbstractTMGateway gateway(String gatewayServiceName);
}
