package com.Onboarding3.AMS.repository;

import com.Onboarding3.AMS.entity.Maintenance;
import com.Onboarding3.AMS.entity.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaintenanceRepository extends JpaRepository<Maintenance, Integer> {
    List<Maintenance> findByOwnerId(Integer ownerId);

    @Query("SELECT DISTINCT m.ownerId FROM Maintenance m")
    List<Integer> getAllOwnerIds();


}
