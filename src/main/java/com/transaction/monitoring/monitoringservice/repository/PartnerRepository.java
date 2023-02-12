package com.transaction.monitoring.monitoringservice.repository;

import com.transaction.monitoring.monitoringservice.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Admin on 7/24/20.
 */

@Repository
public interface PartnerRepository extends JpaRepository<Partner,Integer> {

    @Query(value = "SELECT p FROM Partner p WHERE p.username=?1")
    Partner findByUsername(String username);

    @Query(value="SELECT p FROM Partner p WHERE p.partnerId=?1")
    Optional<Partner> findById(Integer partnerId);

}
