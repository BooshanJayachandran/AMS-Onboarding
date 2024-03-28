package com.Onboarding3.AMS.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.Onboarding3.AMS.entity.Flat;
import com.Onboarding3.AMS.service.FlatService;
import java.util.List;

@RestController
@RequestMapping("/flat")
public class FlatController {

    @Autowired
    private FlatService flatService;

    @PostMapping("/create")
    public ResponseEntity<Flat> createFlat(@RequestBody Flat flat) {
        Flat createdFlat = flatService.createFlat(flat);
        if (createdFlat != null) {
            String message = "Flat " + flat.getFlatNo() + " created successfully.";
            System.out.println(message);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdFlat);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/get")
    public ResponseEntity<Flat> getFlatByFlatNo(@RequestParam Integer flatNo) {
        Flat flat = flatService.getFlatByFlatNo(flatNo);
        if (flat != null) {
            return ResponseEntity.ok().body(flat);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Flat>> getAllFlats() {
        List<Flat> flatList = flatService.getAllFlats();
        return ResponseEntity.ok().body(flatList);
    }

    @PutMapping("/update")
    public ResponseEntity<Flat> updateFlat(@RequestParam Integer flatNo, @RequestBody Flat flat) {
        Flat updatedFlat = flatService.updateFlat(flatNo, flat);
        if (updatedFlat != null) {
            String message = "Flat " + flatNo + " updated successfully.";
            System.out.println(message);
            return ResponseEntity.ok().body(updatedFlat);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteFlat(@RequestParam Integer flatNo) {
        boolean deleted = flatService.deleteFlat(flatNo);
        if (deleted) {
            String message = "Flat " + flatNo + " deleted successfully.";
            System.out.println(message);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
