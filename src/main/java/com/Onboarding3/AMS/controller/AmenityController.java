package com.Onboarding3.AMS.controller;

import com.Onboarding3.AMS.entity.Admin;
import com.Onboarding3.AMS.entity.Amenity;
import com.Onboarding3.AMS.service.AmenityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/amenities")
public class AmenityController {

    private static final Logger logger = LoggerFactory.getLogger(AmenityController.class);

    @Autowired
    private AmenityService amenityService;

    @PostMapping("/create")
    public ResponseEntity<Amenity> createAmenity(@RequestBody Amenity amenity) {
        Amenity createdAmenity = amenityService.createAmenity(amenity);
        logger.info("Amenity {} created successfully", createdAmenity.getAmenityId());
        return new ResponseEntity<>(createdAmenity, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<Amenity> getAmenityById(@RequestParam Integer amenityId) {
        Amenity amenity = amenityService.getAmenityById(amenityId);
        return ResponseEntity.ok(amenity);
    }

    @GetMapping("/getall")
    public ResponseEntity<Object> getAllAmenities() {
        List<Amenity> amenities = amenityService.getAllAmenities();
        return ResponseEntity.ok().body(amenities);
    }

    @PutMapping("/update")
    public ResponseEntity<Amenity> updateAmenity(@RequestBody Integer amenityId, @RequestBody Amenity amenity) {
        Amenity updatedAmenity = amenityService.updateAmenity(amenityId, amenity);
        return ResponseEntity.ok(updatedAmenity);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteAmenity(@RequestParam Integer amenityId) {
        amenityService.deleteAmenity(amenityId);
        logger.info("Amenity {} deleted successfully", amenityId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/deleteById")
    public void deleteAmenityById(@RequestParam Integer amenityId) {
        amenityService.deleteAmenityById(amenityId);
        logger.info("Owner ({}) deleted successfully", amenityId);
    }
}
