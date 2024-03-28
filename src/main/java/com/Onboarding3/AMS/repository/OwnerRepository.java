package com.Onboarding3.AMS.repository;

import com.Onboarding3.AMS.entity.Amenity;
import com.Onboarding3.AMS.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OwnerRepository extends JpaRepository<Owner, Integer> {
    @Query("SELECT MAX(o.ownerId) FROM Owner o")
    Integer getMaxOwnerId();
}