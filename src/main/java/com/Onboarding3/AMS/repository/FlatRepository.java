package com.Onboarding3.AMS.repository;

import com.Onboarding3.AMS.entity.Flat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FlatRepository extends JpaRepository<Flat, Integer> {
    @Query("SELECT MAX(f.flatNo) FROM Flat f")
    Integer getMaxFlatId();
}
