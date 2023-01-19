package com.pil.group4.services;

import com.pil.group4.models.ClassificationType;
import com.pil.group4.models.RecyclingZoneModel;

import java.util.ArrayList;
import java.util.Optional;

public interface IRecyclingZoneService {
    ArrayList<RecyclingZoneModel> getRecyclingZones();

    Optional<RecyclingZoneModel> getRecyclingZoneById(Long idRecyclingZone);

    RecyclingZoneModel saveRecyclingZone(RecyclingZoneModel recyclingZoneModel);

    boolean deleteOfRecyclingZone(Long idRecyclingZone);

    RecyclingZoneModel updateRecyclingZoneById(Long idRecyclingZone, RecyclingZoneModel recyclingZoneModel);

    boolean addSupervisor(Long id, Long idSupervisor);

    Optional<RecyclingZoneModel> changeClassificationType(Long id, Long SupervisorId, RecyclingZoneModel recyclingZone);
}
