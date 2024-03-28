package com.Onboarding3.AMS.repository;

import com.Onboarding3.AMS.entity.CheckInOut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CheckInOutRepository extends JpaRepository<CheckInOut, Integer> {
    CheckInOut findByUserId(Integer userId);

    boolean existsByUserIdAndCheckOutTimeIsNull(Integer userId);
    CheckInOut findLatestCheckInByUserId(Integer userId);

    List<CheckInOut> findByDateAndCheckInTimeBetween(LocalDate date, LocalDateTime checkInTimeStart, LocalDateTime checkInTimeEnd);

}
