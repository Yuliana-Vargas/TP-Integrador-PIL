package com.pil.group4.repositories;

import com.pil.group4.models.RecyclingZoneModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecyclingZoneRepository extends JpaRepository<RecyclingZoneModel, Long> {
}