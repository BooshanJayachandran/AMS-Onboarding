package com.Onboarding3.AMS.controller;

import com.Onboarding3.AMS.entity.Vendor;
import com.Onboarding3.AMS.service.VendorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendor")
public class VendorController {

    private static final Logger logger = LoggerFactory.getLogger(VendorController.class);

    @Autowired
    private VendorService vendorService;

    // Create a new vendor
    @PostMapping("/create")
    public ResponseEntity<Vendor> createVendor(@RequestBody Vendor vendor) {
        Vendor createdVendor = vendorService.createVendor(vendor);
        logger.info("Vendor {} created successfully", createdVendor.getVendorId());
        return new ResponseEntity<>(createdVendor, HttpStatus.CREATED);
    }

    @PostMapping("/createWithAmenity")
    public ResponseEntity<Vendor> createVendorWithAmenity(@RequestBody Vendor vendor) {
        try {
            Vendor createdVendor = vendorService.createVendorWithAmenity(vendor);
            logger.info("Vendor {} created successfully with Amenity {}", createdVendor.getVendorId(), vendor.getAmenityName());
            return ResponseEntity.status(HttpStatus.CREATED).body(createdVendor);
        } catch (Exception e) {
            logger.error("Failed to create vendor with Amenity: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

//key:vendorID value:"id number of vendor"
    @GetMapping("/get")
    public ResponseEntity<Vendor> getVendorById(@RequestParam Integer vendorId) {
        Vendor vendor = vendorService.getVendorById(vendorId);
        if (vendor != null) {
            return ResponseEntity.ok(vendor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @PatchMapping("/update")
    public ResponseEntity<Vendor> patchUpdateVendor(@RequestParam Integer vendorId, @RequestBody Vendor updatedVendor) {
        Vendor existingVendor = vendorService.getVendorById(vendorId);
        if (existingVendor != null) {
            existingVendor = vendorService.patchUpdateVendor(existingVendor, updatedVendor);
            if (existingVendor != null) {
                return ResponseEntity.ok(existingVendor);
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    // Delete a vendor
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteVendor(@RequestParam Integer vendorId) {
        vendorService.deleteVendor(vendorId);
        logger.info("Vendor {} deleted successfully", vendorId);
        return ResponseEntity.noContent().build();
    }


    // Get all vendors
    @GetMapping("/getall")
    public ResponseEntity<List<Vendor>> getAllVendors() {
        List<Vendor> vendors = vendorService.getAllVendors();
        return ResponseEntity.ok(vendors);
    }
}
