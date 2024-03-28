package com.Onboarding3.AMS.service;

import com.Onboarding3.AMS.entity.Amenity;

import java.util.List;

public interface AmenityService {
    Amenity createAmenity(Amenity amenity);
    Amenity getAmenityById(Integer amenityId);
    Amenity updateAmenity(Integer amenityId, Amenity amenity);
    void deleteAmenity(Integer amenityId);
    List<Amenity> getAllAmenities();

    void deleteAmenityById(Integer amenityId);
}
