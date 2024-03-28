package com.Onboarding3.AMS.service;

import com.Onboarding3.AMS.entity.Owner;
import com.Onboarding3.AMS.repository.OwnerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OwnerServiceImpl implements OwnerService {

    private static final Logger logger = LoggerFactory.getLogger(OwnerServiceImpl.class);

    @Autowired
    private OwnerRepository ownerRepository;

    @Override
    public Owner createOwner(Owner owner) {
        owner.setOwnerId(generateOwnerId());
        Owner createdOwner = ownerRepository.save(owner);
        logger.info("Owner ({}) created successfully", createdOwner.getOwnerId());
        return createdOwner;
    }

    @Override
    public Owner getOwnerById(Integer ownerId) {
        return ownerRepository.findById(ownerId).orElse(null);
    }

    @Override
    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }

    @Override
    public Owner updateOwnerById(Integer ownerId, Owner owner) {
        Owner existingOwner = ownerRepository.findById(ownerId).orElse(null);
        if (existingOwner != null) {
            owner.setOwnerId(existingOwner.getOwnerId());
            ownerRepository.save(owner);
            logger.info("Owner ({}) updated successfully", ownerId);
            return owner;
        } else {
            return null;
        }
    }

    @Override
    public Owner patchOwnerById(Integer ownerId, Owner owner) {
        Owner existingOwner = ownerRepository.findById(ownerId).orElse(null);
        if (existingOwner != null) {
            if (owner.getName() != null) {
                existingOwner.setName(owner.getName());
            }
            if (owner.getEmail() != null) {
                existingOwner.setEmail(owner.getEmail());
            }
            ownerRepository.save(existingOwner);
            logger.info("Owner ({}) patched successfully", ownerId);
            return existingOwner;
        } else {
            return null;
        }
    }

    @Override
    public void deleteOwnerById(Integer ownerId) {
        ownerRepository.deleteById(ownerId);
        logger.info("Owner ({}) deleted successfully", ownerId);
    }

    @Override
    public void deleteAllOwners() {
        ownerRepository.deleteAll();
        logger.info("All owners deleted successfully");
    }

    private Integer generateOwnerId() {
        Integer maxOwnerId = ownerRepository.getMaxOwnerId();
        return (maxOwnerId != null && maxOwnerId >= 1000) ? maxOwnerId + 1 : 1000;
    }
}

