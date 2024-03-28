package com.Onboarding3.AMS.service;

import com.Onboarding3.AMS.entity.Vendor;

import java.util.List;

public interface VendorService {
    Vendor createVendor(Vendor vendor);
    Vendor getVendorById(Integer vendorId);
    //Vendor updateVendor(Integer vendorId, Vendor vendor);
    void deleteVendor(Integer vendorId);
     Vendor createVendorWithAmenity(Vendor vendor) ;
    List<Vendor> getAllVendors();

    Vendor patchUpdateVendor(Vendor existingVendor, Vendor updatedVendor);
}
