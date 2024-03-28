package com.Onboarding3.AMS.repository;

import com.Onboarding3.AMS.entity.Request;
import com.Onboarding3.AMS.entity.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Integer> {
    List<Request> findByAmenityIdOrderByRequestDateTimeDesc(Integer amenityId);
    List<Request> findByStatusOrderByRequestDateTime(RequestStatus status);
    List<Request> findByAmenityId(Integer amenityId);
    List<Request> findByOwnerId(Integer ownerId);
}
