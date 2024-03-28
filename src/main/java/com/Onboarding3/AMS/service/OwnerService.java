package com.Onboarding3.AMS.service;

import com.Onboarding3.AMS.entity.Owner;

import java.util.List;

public interface OwnerService {
    Owner createOwner(Owner owner);
    Owner getOwnerById(Integer ownerId);
    List<Owner> getAllOwners();
    Owner updateOwnerById(Integer ownerId, Owner owner);
    Owner patchOwnerById(Integer ownerId, Owner owner);
    void deleteAllOwners();
    void deleteOwnerById(Integer ownerId);
}
