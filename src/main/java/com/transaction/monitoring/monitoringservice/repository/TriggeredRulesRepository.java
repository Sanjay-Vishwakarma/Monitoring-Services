package com.transaction.monitoring.monitoringservice.repository;

import com.transaction.monitoring.monitoringservice.entity.TriggeredRules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Admin on 7/30/20.
 */
@Repository
public interface TriggeredRulesRepository extends JpaRepository<TriggeredRules,Integer> {
}
