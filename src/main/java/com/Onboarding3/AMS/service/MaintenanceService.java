package com.Onboarding3.AMS.service;

import com.Onboarding3.AMS.entity.Maintenance;

public interface MaintenanceService {
        Maintenance createMaintenance(Maintenance maintenance);
        void updateMaintenanceStatus(Integer maintenanceIde);
        void reconcileMaintenance(Integer ownerId);


        Integer findOwnerWithMostDefaultedMaintenances();
}
