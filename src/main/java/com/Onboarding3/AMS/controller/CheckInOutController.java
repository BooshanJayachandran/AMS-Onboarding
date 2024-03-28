package com.Onboarding3.AMS.controller;

import com.Onboarding3.AMS.entity.CheckInOut;
import com.Onboarding3.AMS.service.CheckInOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/checkinout")
public class CheckInOutController {

    @Autowired
    private CheckInOutService checkInOutService;

    @PostMapping("/create")
    public ResponseEntity<CheckInOut> createCheckInOut(@RequestBody CheckInOut checkInOut) {
        CheckInOut createdCheckInOut = checkInOutService.createCheckInOut(checkInOut);
        return new ResponseEntity<>(createdCheckInOut, HttpStatus.CREATED);
    }


    @PostMapping("/checkIn")
    public ResponseEntity<?> checkIn(@RequestBody CheckInOut checkInOut) {
        if (checkInOutService.isUserAlreadyCheckedIn(checkInOut.getUserId())) {
            return new ResponseEntity<>("User is already checked in.", HttpStatus.BAD_REQUEST);
        }

        checkInOut.setCheckInTime(LocalDateTime.now());

        CheckInOut createdCheckInOut = checkInOutService.createCheckInOut(checkInOut);
        return new ResponseEntity<>(createdCheckInOut, HttpStatus.CREATED);
    }


    @PostMapping("/checkOut")
    public ResponseEntity<?> checkOut(@RequestParam("userId") Integer userId) {
        CheckInOut latestCheckInOut = checkInOutService.getLatestCheckInByUserId(userId);

        if (latestCheckInOut == null || latestCheckInOut.getCheckOutTime() != null) {
            return new ResponseEntity<>("User hasn't checked in.", HttpStatus.BAD_REQUEST);
        }

        latestCheckInOut.setCheckOutTime(LocalDateTime.now());

        CheckInOut updatedCheckInOut = checkInOutService.updateCheckInOut(latestCheckInOut.getCheckInOutId(), latestCheckInOut);
        return new ResponseEntity<>(updatedCheckInOut, HttpStatus.OK);
    }


    @GetMapping("/all")
    public ResponseEntity<List<CheckInOut>> getAllCheckInOuts() {
        List<CheckInOut> checkInOuts = checkInOutService.getAllCheckInOuts();
        return new ResponseEntity<>(checkInOuts, HttpStatus.OK);
    }


    @GetMapping("/getByUserId")
    public ResponseEntity<CheckInOut> getCheckInOutByUserId(@RequestParam("userId") Integer userId) {
        CheckInOut checkInOut = checkInOutService.getCheckInOutByUserId(userId);
        if (checkInOut != null) {
            return new ResponseEntity<>(checkInOut, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/checkInOut")
    public ResponseEntity<List<CheckInOut>> findByDateTimeRange(@RequestParam("date") LocalDate date, @RequestParam("checkInTime") LocalDateTime checkInTime, @RequestParam("checkOutTime") LocalDateTime checkOutTime){
        List<CheckInOut> result = checkInOutService.findByDateTimeRange(date, checkInTime, checkOutTime);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<CheckInOut> updateCheckInOut(@RequestParam("userId") Integer userId, @RequestBody CheckInOut checkInOut) {
        CheckInOut updatedCheckInOut = checkInOutService.updateCheckInOut(userId, checkInOut);
        if (updatedCheckInOut != null) {
            return new ResponseEntity<>(updatedCheckInOut, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteCheckInOut(@RequestParam("userId") Integer userId) {
        boolean deleted = checkInOutService.deleteCheckInOut(userId);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
