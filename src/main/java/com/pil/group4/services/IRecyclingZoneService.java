package com.pil.group4.services;

import com.pil.group4.models.ClassificationType;
import com.pil.group4.models.OccupationCapacity;
import com.pil.group4.models.RecyclingZoneModel;
import com.pil.group4.models.StateOfTheZone;

import java.util.List;
import java.util.Optional;

public interface IRecyclingZoneService {
    List<RecyclingZoneModel> getRecyclingZones();

    Optional<RecyclingZoneModel> getRecyclingZoneById(Long idRecyclingZone);

    RecyclingZoneModel saveRecyclingZone(RecyclingZoneModel recyclingZoneModel);

    String deleteOfRecyclingZone(Long idRecyclingZone);

    RecyclingZoneModel updateRecyclingZoneById(Long idRecyclingZone, RecyclingZoneModel recyclingZoneModel);

    String addSupervisor(Long id, Long idSupervisor);

    String deleteSupervisor(Long id, Long idSupervisor);

    Optional<RecyclingZoneModel> changeClassificationType(Long id, Long SupervisorId, RecyclingZoneModel recyclingZone);

    Optional<RecyclingZoneModel> changeStateOfTheZone(Long id, Long SupervisorId, RecyclingZoneModel recyclingZone);

    //Optional<RecyclingZoneModel> changeLocation(Long id, Long SupervisorId, RecyclingZoneModel recyclingZone);

    Optional<RecyclingZoneModel> getRecyclingZoneBySupervisor(Long idSupervisor);


    //List<RecyclingZoneModel> getRecyclingZonesByStateOfTheZone(StateOfTheZone stateOfTheZone);

    List<RecyclingZoneModel> getRecyclingZonesByDepartment(String department);
    
    Optional<RecyclingZoneModel> needsReclassification(Long id, Long SupervisorId, RecyclingZoneModel recyclingZone);

    List<RecyclingZoneModel> findRecyclingZoneByClassificationType(ClassificationType classificationType);

    List<RecyclingZoneModel> findRecyclingZoneByOccupationCapacity(OccupationCapacity occupationCapacity);

    List<RecyclingZoneModel> findRecyclingZoneByStateOfTheZone(StateOfTheZone stateOfTheZone);
}
