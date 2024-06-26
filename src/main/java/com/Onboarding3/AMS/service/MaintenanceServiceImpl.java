package com.Onboarding3.AMS.service;

import com.Onboarding3.AMS.entity.Maintenance;
import com.Onboarding3.AMS.entity.PaymentStatus;
import com.Onboarding3.AMS.repository.MaintenanceRepository;
import com.Onboarding3.AMS.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {

    @Autowired
    private MaintenanceRepository maintenanceRepository;

    @Autowired
    private PaymentRepository paymentRepository;


    @Override
    public Maintenance createMaintenance(Maintenance maintenance) {
        maintenance.setStatus(PaymentStatus.NOT_PAID);

        if (maintenance.getAmount() != null) {
            List<Maintenance> existingMaintenanceRecords = maintenanceRepository.findByOwnerId(maintenance.getOwnerId());
            if (!existingMaintenanceRecords.isEmpty()) {
                int totalAmountPaid = existingMaintenanceRecords.stream()
                        .mapToInt(Maintenance::getAmountPayable)
                        .sum();
                int newAmountPayable = totalAmountPaid + maintenance.getAmount().intValue();
                maintenance.setAmountPayable(newAmountPayable);
            } else {
                maintenance.setAmountPayable((int) Math.round(maintenance.getAmount()));
            }
        }

        Maintenance createdMaintenance = maintenanceRepository.save(maintenance);

        reconcileMaintenance(maintenance.getOwnerId());

        return createdMaintenance;
    }

    public void reconcileMaintenance(Integer ownerId) {
        List<Maintenance> maintenanceRecords = maintenanceRepository.findByOwnerId(ownerId);

        if (maintenanceRecords.size() == 1) {
            Maintenance maintenance = maintenanceRecords.get(0);
            if (maintenance.getStatus() == PaymentStatus.NOT_PAID) {
                maintenance.setCharge(0); // No charge for the first maintenance
            }
            maintenanceRepository.save(maintenance);
            return;
        }

        for (int i = 1; i < maintenanceRecords.size(); i++) {
            Maintenance currentMaintenance = maintenanceRecords.get(i);
            Maintenance previousMaintenance = maintenanceRecords.get(i - 1);

            int amountPaid = paymentRepository.findTotalAmountPaidByMaintenanceId(previousMaintenance.getMaintenanceId());
            int remainingAmount = previousMaintenance.getAmountPayable() - amountPaid;

            int charge = 0;
            if (previousMaintenance.getStatus() == PaymentStatus.PARTIALLY_PAID) {
                charge = 0;
            } else if (previousMaintenance.getStatus() == PaymentStatus.NOT_PAID) {
                charge = 800;
            }

            int currentAmountPayable = remainingAmount + currentMaintenance.getAmount().intValue() + charge;

            currentMaintenance.setCharge(charge);
            currentMaintenance.setAmountPayable(currentAmountPayable);

            updateMaintenanceStatus(currentMaintenance.getMaintenanceId());
            maintenanceRepository.save(currentMaintenance);
        }
    }


    @Override
    public void updateMaintenanceStatus(Integer maintenanceId) {
        Maintenance maintenance = maintenanceRepository.findById(maintenanceId).orElse(null);
        if (maintenance != null) {
            int totalPaidAmount = paymentRepository.findTotalAmountPaidByMaintenanceId(maintenanceId);
            if (totalPaidAmount == 0) {
                maintenance.setStatus(PaymentStatus.NOT_PAID);
            } else if (totalPaidAmount == maintenance.getAmountPayable()) {
                maintenance.setStatus(PaymentStatus.PAID);
            } else {
                maintenance.setStatus(PaymentStatus.PARTIALLY_PAID);
            }
            maintenanceRepository.save(maintenance);
        }
    }

//    @Override
//    public Integer findOwnerWithMostDefaultedMaintenances() {
//        List<Integer> ownerIds = maintenanceRepository.getAllOwnerIds();
//        Map<Integer, Integer> defaultedMaintenanceCounts = new HashMap<>();
//
//        for (Integer ownerId : ownerIds) {
//            int noOfDefaults = countDefaultedMaintenances(ownerId);
//            defaultedMaintenanceCounts.put(ownerId, noOfDefaults);
//        }
//
//        Integer mostDefaultedOwner = null;
//        int maxDefaults = 0;
//        for (Map.Entry<Integer, Integer> entry : defaultedMaintenanceCounts.entrySet()) {
//            Integer ownerId = entry.getKey();
//            Integer count = entry.getValue();
//            if (count > maxDefaults) {
//                maxDefaults = count;
//                mostDefaultedOwner = ownerId;
//            }
//        }
//
//        return mostDefaultedOwner;
//    }

    @Override
    public Map<Integer, Integer> findTopNDefaulters(int n) {
        List<Integer> ownerIds = maintenanceRepository.getAllOwnerIds();
        Map<Integer, Integer> defaultedMaintenanceCounts = new HashMap<>();

        for (Integer ownerId : ownerIds) {
            int noOfDefaults = countDefaultedMaintenances(ownerId);
            defaultedMaintenanceCounts.put(ownerId, noOfDefaults);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> topNDefaultersQueue = new PriorityQueue<>(
                (a, b) -> b.getValue().compareTo(a.getValue()));

        for (Map.Entry<Integer, Integer> entry : defaultedMaintenanceCounts.entrySet()) {
            topNDefaultersQueue.offer(entry);
        }

        Map<Integer, Integer> topNDefaulters = new HashMap<>();
        for (int i = 0; i < n && !topNDefaultersQueue.isEmpty(); i++) {
            Map.Entry<Integer, Integer> entry = topNDefaultersQueue.poll();
            topNDefaulters.put(entry.getKey(), entry.getValue());
        }

        return topNDefaulters;
    }

    private int countDefaultedMaintenances(Integer ownerId) {
        List<Maintenance> maintenances = maintenanceRepository.findByOwnerId(ownerId);
        int noOfDefaults = 0;
        for (Maintenance maintenance : maintenances) {
            if (maintenance.getStatus() == PaymentStatus.NOT_PAID) {
                noOfDefaults++;
            }
        }
        return noOfDefaults;
    }


}
