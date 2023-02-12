package com.transaction.monitoring.monitoringservice.repository;

import com.transaction.monitoring.monitoringservice.entity.MonitoringRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Admin on 7/28/20.
 */
@Repository
public interface TransactionRepository extends JpaRepository<MonitoringRequest,Integer> {

}
