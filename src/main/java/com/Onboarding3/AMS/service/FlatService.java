package com.Onboarding3.AMS.service;

import com.Onboarding3.AMS.entity.Flat;

import java.util.List;

public interface FlatService {
    Flat createFlat(Flat flat);
    Flat getFlatByFlatNo(Integer flatNo);
    List<Flat> getAllFlats();
    Flat updateFlat(Integer flatNo, Flat flat);
    boolean deleteFlat(Integer flatNo);
}
