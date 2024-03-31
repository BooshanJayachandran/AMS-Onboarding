package com.Onboarding3.AMS.service;

import com.Onboarding3.AMS.entity.Maintenance;

import java.util.List;
import java.util.Map;

public interface MaintenanceService {
        Maintenance createMaintenance(Maintenance maintenance);
        void updateMaintenanceStatus(Integer maintenanceIde);
        void reconcileMaintenance(Integer ownerId);
        Map<Integer, Integer> findTopNDefaulters(int n);

}
