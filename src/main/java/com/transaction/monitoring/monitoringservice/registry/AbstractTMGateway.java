package com.transaction.monitoring.monitoringservice.registry;

import com.transaction.monitoring.monitoringservice.entity.MonitoringRequest;
import com.transaction.monitoring.monitoringservice.entity.MonitoringResponse;


public abstract class AbstractTMGateway {

    public abstract MonitoringResponse processTransaction(MonitoringRequest request);

    public abstract MonitoringResponse updateTransaction(MonitoringRequest request);
}
