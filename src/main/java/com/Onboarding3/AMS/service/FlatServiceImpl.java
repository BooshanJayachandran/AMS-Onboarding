package com.Onboarding3.AMS.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Onboarding3.AMS.entity.Flat;
import com.Onboarding3.AMS.repository.FlatRepository;
import java.util.List;
import java.util.Optional;

@Service
public class FlatServiceImpl implements FlatService {

    @Autowired
    private FlatRepository flatRepository;

    @Override
    public Flat createFlat(Flat flat) {
        return flatRepository.save(flat);
    }

    @Override
    public Flat getFlatByFlatNo(Integer flatNo) {
        Optional<Flat> flatOptional = flatRepository.findById(flatNo);
        return flatOptional.orElse(null);
    }

    @Override
    public List<Flat> getAllFlats() {
        return flatRepository.findAll();
    }

    @Override
    public Flat updateFlat(Integer flatNo, Flat flat) {
        Optional<Flat> existingFlatOptional = flatRepository.findById(flatNo);
        if (existingFlatOptional.isPresent()) {
            flat.setFlatNo(flatNo);
            return flatRepository.save(flat);
        } else {
            return null;
        }
    }

    @Override
    public boolean deleteFlat(Integer flatNo) {
        Optional<Flat> existingFlatOptional = flatRepository.findById(flatNo);
        if (existingFlatOptional.isPresent()) {
            flatRepository.deleteById(flatNo);
            return true;
        } else {
            return false;
        }
    }
}
