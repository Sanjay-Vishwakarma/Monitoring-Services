package com.transaction.monitoring.monitoringservice.repository;

import com.transaction.monitoring.monitoringservice.entity.AccountMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by Admin on 7/24/2020.
 */

@Repository
public interface AccountMappingRepository extends JpaRepository<AccountMapping,Integer> {

    @Query(value="SELECT f FROM AccountMapping f WHERE f.partnerid=?1")
    List<AccountMapping> findByPartnerId(Integer partnerId);
}
