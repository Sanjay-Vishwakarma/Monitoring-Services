package com.transaction.monitoring.monitoringservice.repository;

import com.transaction.monitoring.monitoringservice.entity.RiskRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by Admin on 8/12/2020.
 */
public interface RiskRuleRepository extends JpaRepository<RiskRule,Integer> {

    @Query(value="SELECT r.score FROM RiskRule r WHERE r.ruleId=?1 AND r.partnerId=?2")
    public String getScore(String ruleId,String partnerId);
}
