package com.Onboarding3.AMS.controller;


import com.Onboarding3.AMS.entity.Owner;
import com.Onboarding3.AMS.service.OwnerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owner")
public class OwnerController {

    private static final Logger logger = LoggerFactory.getLogger(OwnerController.class);

    @Autowired
    private OwnerService ownerService;

    @PostMapping("/create")
    public ResponseEntity<Owner> createOwner(@RequestBody Owner owner) {
        Owner createdOwner = ownerService.createOwner(owner);
        logger.info("Owner ({}) created successfully", createdOwner.getOwnerId());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOwner);
    }

    @GetMapping("/getById")
    public ResponseEntity<Owner> getOwnerById(@RequestParam Integer ownerId) {
        Owner owner = ownerService.getOwnerById(ownerId);
        if (owner != null) {
            logger.info("Owner ({}) retrieved successfully", ownerId);
            return ResponseEntity.ok(owner);
        } else {
            logger.error("Owner with ID ({}) not found", ownerId);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Owner>> getAllOwners() {
        List<Owner> owners = ownerService.getAllOwners();
        logger.info("Retrieved all owners successfully");
        return ResponseEntity.ok(owners);
    }

    @PutMapping("/updateById")
    public ResponseEntity<Owner> updateOwnerById(@RequestParam Integer ownerId, @RequestBody Owner owner) {
        Owner updatedOwner = ownerService.updateOwnerById(ownerId, owner);
        if (updatedOwner != null) {
            logger.info("Owner ({}) updated successfully", ownerId);
            return ResponseEntity.ok(updatedOwner);
        } else {
            logger.error("Failed to update owner with ID ({})", ownerId);
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/patchById")
    public ResponseEntity<Owner> patchOwnerById(@RequestParam Integer ownerId, @RequestBody Owner owner) {
        Owner patchedOwner = ownerService.patchOwnerById(ownerId, owner);
        if (patchedOwner != null) {
            logger.info("Owner ({}) patched successfully", ownerId);
            return ResponseEntity.ok(patchedOwner);
        } else {
            logger.error("Failed to patch owner with ID ({})", ownerId);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<Void> deleteOwnerById(@RequestParam Integer ownerId) {
        ownerService.deleteOwnerById(ownerId);
        logger.info("Owner ({}) deleted successfully", ownerId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Void> deleteAllOwners() {
        ownerService.deleteAllOwners();
        logger.info("All owners deleted successfully");
        return ResponseEntity.noContent().build();
    }
}
