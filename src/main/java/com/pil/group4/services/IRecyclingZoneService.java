package com.pil.group4.services;

import com.pil.group4.models.*;

import java.awt.*;
import java.util.List;
import java.util.Optional;

public interface IRecyclingZoneService {
    List<RecyclingZoneModel> getRecyclingZones();

    Optional<RecyclingZoneModel> getRecyclingZoneById(Long idRecyclingZone);

    RecyclingZoneModel saveRecyclingZone(RecyclingZoneModel recyclingZoneModel);

    String deleteOfRecyclingZone(Long idRecyclingZone);

    RecyclingZoneModel updateRecyclingZoneById(RecyclingZoneModel newRecyclingZoneModel, Long id);

    String addSupervisor(Long id, Long idSupervisor);

    String deleteSupervisor(Long id, Long idSupervisor);

    Optional<RecyclingZoneModel> changeClassificationType(Long id, Long SupervisorId, RecyclingZoneModel recyclingZone);

    Optional<RecyclingZoneModel> changeStateOfTheZone(Long id, Long SupervisorId, RecyclingZoneModel recyclingZone);

    Optional<RecyclingZoneModel> changeOccupationCapacity(Long id, Long SupervisorId, RecyclingZoneModel recyclingZone);

    Optional<RecyclingZoneModel> getRecyclingZoneBySupervisor(Long idSupervisor);

    List<RecyclingZoneModel> getRecyclingZonesByDepartment(String department);

    Optional<RecyclingZoneModel> needsReclassification(Long id, Long SupervisorId, RecyclingZoneModel recyclingZone);

    List<RecyclingZoneModel> findRecyclingZoneByClassificationType(ClassificationType classificationType);

    List<RecyclingZoneModel> findRecyclingZoneByOccupationCapacity(OccupationCapacity occupationCapacity);

    List<RecyclingZoneModel> findRecyclingZoneByStateOfTheZone(StateOfTheZone stateOfTheZone);

    String shortestRoute(List<Integer> idsRecZone, Point startingPoint);

    String addComplaint(Long id, ComplaintModel complaintModel);

    String deleteComplaint(Long id, Long idComplaint);

    String cleanComplaints(Long id);

    List<ComplaintModel> getRecyclingZoneComplaints(Long id);

}
