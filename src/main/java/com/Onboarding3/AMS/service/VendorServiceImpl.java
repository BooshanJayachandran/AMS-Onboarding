package com.Onboarding3.AMS.service;

import com.Onboarding3.AMS.entity.Amenity;
import com.Onboarding3.AMS.repository.AmenityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Onboarding3.AMS.entity.Vendor;
import com.Onboarding3.AMS.repository.VendorRepository;
import java.util.List;

@Service
public class VendorServiceImpl implements VendorService {

    @Autowired
    private VendorRepository vendorRepository;
    @Autowired
    private AmenityRepository amenityRepository;


    @Override
    public Vendor createVendor(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    @Override
    public Vendor createVendorWithAmenity(Vendor vendor) {
        Amenity amenity = amenityRepository.findByAmenityName(vendor.getAmenityName());
        if (amenity == null) {
            throw new IllegalArgumentException("Amenity with name " + vendor.getAmenityName() + " does not exist.");
        }
        vendor.setAmenityName(vendor.getAmenityName());

        return vendorRepository.save(vendor);
    }


    @Override
    public Vendor getVendorById(Integer vendorId) {
        return vendorRepository.findById(vendorId).orElse(null);
    }


    @Override
    public Vendor patchUpdateVendor(Vendor existingVendor, Vendor updatedVendor) {
        if (existingVendor != null && updatedVendor != null) {
            if (updatedVendor.getName() != null) {
                existingVendor.setName(updatedVendor.getName());
            }
            if (updatedVendor.getEmail() != null) {
                existingVendor.setEmail(updatedVendor.getEmail());
            }
            if (updatedVendor.getAmenityName() != null) {
                existingVendor.setAmenityName(updatedVendor.getAmenityName());
            }
            return vendorRepository.save(existingVendor);
        }
        return null;
    }

    @Override
    public void deleteVendor(Integer vendorId) {
        vendorRepository.deleteById(vendorId);
    }

    @Override
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }
}
