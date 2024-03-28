package com.Onboarding3.AMS.service;

import com.Onboarding3.AMS.entity.CheckInOut;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface CheckInOutService {

    CheckInOut createCheckInOut(CheckInOut checkInOut);

    List<CheckInOut> getAllCheckInOuts();

    //CheckInOut getCheckInOutById(Integer userId);

    CheckInOut getCheckInOutByUserId(Integer userId);


    CheckInOut updateCheckInOut(Integer userId, CheckInOut checkInOut);

    boolean deleteCheckInOut(Integer userId);

    boolean isUserAlreadyCheckedIn(Integer userId);

    CheckInOut getLatestCheckInByUserId(Integer userId);

    List<CheckInOut> findByDateTimeRange(LocalDate date, LocalDateTime checkInTime, LocalDateTime checkOutTime);

}
