package com.pil.group4.services;

import com.pil.group4.models.RecyclingZoneModel;
import com.pil.group4.models.SupervisorModel;
import com.pil.group4.repositories.RecyclingZoneRepository;
import com.pil.group4.repositories.SupervisorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RecyclingZoneService implements IRecyclingZoneService {
    private final RecyclingZoneRepository recyclingZoneRepository;

    private final SupervisorRepository supervisorRepository;

    @Autowired
    public RecyclingZoneService(RecyclingZoneRepository recyclingZoneRepository, SupervisorRepository supervisorRepository) {
        this.recyclingZoneRepository = recyclingZoneRepository;
        this.supervisorRepository = supervisorRepository;
    }

    @Override
    public List<RecyclingZoneModel> getRecyclingZones() {
        return recyclingZoneRepository.findAll();
    }

    @Override
    public Optional<RecyclingZoneModel> getRecyclingZoneById(Long idRecyclingZone) {
        return recyclingZoneRepository.findById(idRecyclingZone);
    }

    @Override
    public RecyclingZoneModel saveRecyclingZone(RecyclingZoneModel recyclingZoneModel) {
        return recyclingZoneRepository.save(recyclingZoneModel);
    }

    @Override
    public boolean deleteOfRecyclingZone(Long id) {
        try {
            recyclingZoneRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public RecyclingZoneModel updateRecyclingZoneById(@PathVariable("id") Long id, @RequestBody RecyclingZoneModel recyclingZoneDetails) {
        Optional<RecyclingZoneModel> optionalUpdate = recyclingZoneRepository.findById(id);
        RecyclingZoneModel update = optionalUpdate.get();
        update.setName(recyclingZoneDetails.getName());
        return recyclingZoneRepository.save(update);
    }

    public String addSupervisor(Long id, Long idSupervisor) {
        Optional<RecyclingZoneModel> optionalUpdate = recyclingZoneRepository.findById(id);
        Optional<SupervisorModel> optionalSupervisor = supervisorRepository.findById(idSupervisor);
        if (optionalUpdate.isPresent() && optionalSupervisor.isPresent()) {
            RecyclingZoneModel update = optionalUpdate.get();
            SupervisorModel supervisor = optionalSupervisor.get();
            update.setSupervisor(supervisor);
            recyclingZoneRepository.save(update);
            return "Supervisor with id " + idSupervisor + " added to recycling zone with id " + id;
        } else {
            return "Supervisor with id " + idSupervisor + " or recycling zone with id " + id + " not found";
        }
    }

    @Override
    public String deleteSupervisor(Long id, Long idSupervisor) {
        Optional<RecyclingZoneModel> optionalUpdate = recyclingZoneRepository.findById(id);
        Optional<SupervisorModel> optionalSupervisor = supervisorRepository.findById(idSupervisor);
        if (optionalUpdate.isEmpty() || optionalSupervisor.isEmpty()) {
            return "Supervisor with id " + idSupervisor + " or recycling zone with id " + id + " not found";
        }
        RecyclingZoneModel update = optionalUpdate.get();
        if ((update.getSupervisor() != null) && Objects.equals(update.getSupervisor().getId(), idSupervisor)){
            update.setSupervisor(null);
            recyclingZoneRepository.save(update);
            return "Supervisor with id " + idSupervisor + " deleted from recycling zone with id " + id;
        }
        return "Supervisor with id " + idSupervisor + " not found in recycling zone with id " + id;
    }

    @Override
    public Optional<RecyclingZoneModel> getRecyclingZoneBySupervisor(Long idSupervisor) {
        List<RecyclingZoneModel> recyclingZones = recyclingZoneRepository.findAll();
        for (RecyclingZoneModel recyclingZone : recyclingZones) {
            if(!Objects.equals(recyclingZone.getSupervisor(), null) &&
                    Objects.equals(recyclingZone.getSupervisor().getId(), idSupervisor)) {
                return Optional.of(recyclingZone);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<RecyclingZoneModel> changeClassificationType(Long id, Long SupervisorId, RecyclingZoneModel recyclingZone) {
        Optional<RecyclingZoneModel> optionalUpdate = recyclingZoneRepository.findById(id);
        Optional<SupervisorModel> optionalSupervisor = supervisorRepository.findById(SupervisorId);
        if (optionalUpdate.isEmpty() || optionalSupervisor.isEmpty() || optionalUpdate.get().getSupervisor() == null ||
                !Objects.equals(optionalUpdate.get().getSupervisor().getId(), SupervisorId)) {
            return Optional.empty();
        }
        RecyclingZoneModel update = optionalUpdate.get();
        update.setClassificationType(recyclingZone.getClassificationType());
        return Optional.of(recyclingZoneRepository.save(update));
    }

    @Override
    public Optional<RecyclingZoneModel> changeStateOfTheZone(Long id, Long SupervisorId, RecyclingZoneModel recyclingZone) {
        Optional<RecyclingZoneModel> optionalUpdate = recyclingZoneRepository.findById(id);
        Optional<SupervisorModel> optionalSupervisor = supervisorRepository.findById(SupervisorId);
        if (optionalUpdate.isEmpty() || optionalSupervisor.isEmpty() || optionalUpdate.get().getSupervisor() == null ||
                !Objects.equals(optionalUpdate.get().getSupervisor().getId(), SupervisorId)) {
            return Optional.empty();
        }
        RecyclingZoneModel update = optionalUpdate.get();
        update.setStateOfTheZone(recyclingZone.getStateOfTheZone());
        return Optional.of(recyclingZoneRepository.save(update));
    }

    @Override
    public List<RecyclingZoneModel> getRecyclingZonesByDepartment(String department) {
        List<RecyclingZoneModel> recyclingZones = recyclingZoneRepository.findAll();
        ArrayList<RecyclingZoneModel> recyclingZonesByDepartment = new ArrayList<>();

        for (RecyclingZoneModel recyclingZone : recyclingZones) {
            if (!Objects.equals(recyclingZone.getLocation(), null) &&
                    Objects.equals(recyclingZone.getLocation().getDepartment(), department)) {
                recyclingZonesByDepartment.add(recyclingZone);
            }
        }

        return recyclingZonesByDepartment;
    }

}