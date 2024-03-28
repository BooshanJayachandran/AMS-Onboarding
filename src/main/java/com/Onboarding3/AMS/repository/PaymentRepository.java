package com.Onboarding3.AMS.repository;

import com.Onboarding3.AMS.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    @Query("SELECT COALESCE(SUM(p.amountPaid), 0) FROM Payment p WHERE p.maintenanceId = :maintenanceId")
    int findTotalAmountPaidByMaintenanceId(@Param("maintenanceId") Integer maintenanceId);
}
