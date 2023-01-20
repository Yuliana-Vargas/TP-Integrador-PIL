package com.pil.group4.repositories;

import com.pil.group4.models.ClassificationType;
import com.pil.group4.models.OccupationCapacity;
import com.pil.group4.models.RecyclingZoneModel;
import com.pil.group4.models.StateOfTheZone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecyclingZoneRepository extends JpaRepository<RecyclingZoneModel, Long> {
    List<RecyclingZoneModel> findRecyclingZoneByClassificationType(ClassificationType classificationType);

    List<RecyclingZoneModel> findRecyclingZoneByOccupationCapacity(OccupationCapacity occupationCapacity);

    List<RecyclingZoneModel> findRecyclingZoneByStateOfTheZone(StateOfTheZone stateOfTheZone);
}