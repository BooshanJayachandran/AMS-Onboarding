package com.Onboarding3.AMS.service;

import com.Onboarding3.AMS.entity.CheckInOut;
import com.Onboarding3.AMS.repository.CheckInOutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CheckInOutServiceImpl implements CheckInOutService {

    @Autowired
    private CheckInOutRepository checkInOutRepository;

    @Override
    public CheckInOut createCheckInOut(CheckInOut checkInOut) {
        return checkInOutRepository.save(checkInOut);
    }

    @Override
    public List<CheckInOut> getAllCheckInOuts() {
        return checkInOutRepository.findAll();
    }


//    @Override
//    public CheckInOut getCheckInOutById(Integer userId) {
//        return checkInOutRepository.findById(userId).orElse(null);
//    }

    @Override
    public CheckInOut getCheckInOutByUserId(Integer userId) {
        return checkInOutRepository.findByUserId(userId);
    }

//    @Override
//    public List<CheckInOut> findByDateTimeRange(LocalDate date, LocalDateTime checkInTime, LocalDateTime checkOutTime) {
//        return checkInOutRepository.findByDateAndCheckInTimeAndCheckOutTime(date, checkInTime, checkOutTime);
//    }

    @Override
    public List<CheckInOut> findByDateTimeRange(LocalDate date, LocalDateTime checkInTime, LocalDateTime checkOutTime) {
        LocalDateTime checkInTimeStart = LocalDateTime.of(date, checkInTime.toLocalTime());
        LocalDateTime checkInTimeEnd = LocalDateTime.of(date, checkOutTime.toLocalTime());
        return checkInOutRepository.findByDateAndCheckInTimeBetween(date, checkInTimeStart, checkInTimeEnd);
    }

    @Override
    public CheckInOut updateCheckInOut(Integer userId, CheckInOut checkInOut) {
        if (checkInOutRepository.existsById(userId)) {
            checkInOut.setCheckInOutId(userId);
            return checkInOutRepository.save(checkInOut);
        }
        return null;
    }

    @Override
    public boolean deleteCheckInOut(Integer userId) {
        if (checkInOutRepository.existsById(userId)) {
            checkInOutRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    @Override
    public boolean isUserAlreadyCheckedIn(Integer userId) {
        return checkInOutRepository.existsByUserIdAndCheckOutTimeIsNull(userId);
    }

    @Override
    public CheckInOut getLatestCheckInByUserId(Integer userId) {
        return checkInOutRepository.findLatestCheckInByUserId(userId);
    }
}
