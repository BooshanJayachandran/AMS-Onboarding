package com.Onboarding3.AMS.service;

import com.Onboarding3.AMS.entity.Maintenance;
import com.Onboarding3.AMS.entity.Payment;
import com.Onboarding3.AMS.repository.MaintenanceRepository;
import com.Onboarding3.AMS.repository.PaymentRepository;
import com.Onboarding3.AMS.service.MaintenanceService;
import com.Onboarding3.AMS.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Onboarding3.AMS.service.MaintenanceServiceImpl;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private MaintenanceRepository maintenanceRepository;

    @Autowired
    private MaintenanceService maintenanceService;

    @Override
    public Payment makePayment(Payment payment) {
        Payment madePayment = paymentRepository.save(payment);

        // After saving the payment, update the maintenance status
        maintenanceService.updateMaintenanceStatus(payment.getMaintenanceId());

        // Check if there are any existing maintenance records for the owner
        Maintenance maintenance = maintenanceRepository.findById(payment.getMaintenanceId()).orElse(null);
        if (maintenance != null) {
            int ownerId = maintenance.getOwnerId();
            List<Maintenance> existingMaintenanceRecords = maintenanceRepository.findByOwnerId(ownerId);

            // If there are existing maintenance records, reconcile them
            if (!existingMaintenanceRecords.isEmpty()) {
                maintenanceService.reconcileMaintenance(ownerId);
            }
        }

        return madePayment;
    }
}
