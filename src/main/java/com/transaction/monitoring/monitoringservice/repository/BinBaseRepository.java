package com.transaction.monitoring.monitoringservice.repository;

import com.transaction.monitoring.monitoringservice.entity.BinBase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Admin on 7/30/20.
 */
@Repository
public interface BinBaseRepository extends JpaRepository<BinBase,Integer> {
}
