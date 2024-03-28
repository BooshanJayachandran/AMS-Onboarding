package com.Onboarding3.AMS.service;

import com.Onboarding3.AMS.entity.Amenity;
import com.Onboarding3.AMS.repository.AmenityRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;

@Service
public class AmenityServiceImpl implements AmenityService {

    @Autowired
    private AmenityRepository amenityRepository;

    @Override
    public Amenity createAmenity(Amenity amenity) {
        return amenityRepository.save(amenity);
    }

    @Override
    public Amenity getAmenityById(Integer amenityId) {
        return amenityRepository.findById(amenityId).orElse(null);
    }

    @Override
    public Amenity updateAmenity(Integer amenityId, Amenity amenity) {
        Amenity existingAmenity = getAmenityById(amenityId);
        if (existingAmenity != null) {
            amenity.setAmenityId(amenityId);
            return amenityRepository.save(amenity);
        }
        return null;
    }

    @Override
    public void deleteAmenity(Integer amenityId) {
        amenityRepository.deleteById(amenityId);
    }

    @Override
    public List<Amenity> getAllAmenities() {
        return amenityRepository.findAll();
    }

    @Override
    public void deleteAmenityById(Integer amenityId) {

    }

}
