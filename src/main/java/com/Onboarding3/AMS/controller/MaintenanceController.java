package com.Onboarding3.AMS.controller;

import com.Onboarding3.AMS.entity.Maintenance;
import com.Onboarding3.AMS.service.MaintenanceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/maintenance")
public class MaintenanceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MaintenanceController.class);

    @Autowired
    private MaintenanceService maintenanceService;

    @PostMapping("/create")
    public ResponseEntity<Maintenance> createMaintenance(@RequestBody Maintenance maintenance) {
        LOGGER.info("Request received to create maintenance: {}", maintenance);
        try {
            Maintenance createdMaintenance = maintenanceService.createMaintenance(maintenance);
            LOGGER.info("Maintenance created successfully: {}", createdMaintenance);
            return new ResponseEntity<>(createdMaintenance, HttpStatus.CREATED);
        } catch (Exception e) {
            LOGGER.error("Error occurred while creating maintenance", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findOwnerWithMostDefaults")
    public ResponseEntity<Integer> findOwnerWithMostDefaultedMaintenances() {
        try {
            Integer ownerId = maintenanceService.findOwnerWithMostDefaultedMaintenances();
            if (ownerId != null) {
                LOGGER.info("Owner with most defaulted maintenances: {}", ownerId);
                return new ResponseEntity<>(ownerId, HttpStatus.OK);
            } else {
                LOGGER.info("No owners found with defaulted maintenances");
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            LOGGER.error("Error occurred while finding owner with most defaulted maintenances", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
