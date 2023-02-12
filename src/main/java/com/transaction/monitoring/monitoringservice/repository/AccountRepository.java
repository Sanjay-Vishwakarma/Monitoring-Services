package com.transaction.monitoring.monitoringservice.repository;

import com.transaction.monitoring.monitoringservice.entity.Account;
import com.transaction.monitoring.monitoringservice.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by Admin on 7/28/20.
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    @Query(value="SELECT a FROM Account a WHERE a.Id=?1")
    Optional<Account> findById(Integer accountId);
}
