package com.Onboarding3.AMS.repository;

import com.Onboarding3.AMS.entity.Amenity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmenityRepository extends JpaRepository<Amenity, Integer> {
    Amenity findByAmenityName(String amenityName);

}
