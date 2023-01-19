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
    public ArrayList<RecyclingZoneModel> getRecyclingZones() {
        return (ArrayList<RecyclingZoneModel>) recyclingZoneRepository.findAll();
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
    public boolean deleteOfRecyclingZone(Long id){
        try{
            recyclingZoneRepository.deleteById(id);
            return true;
        } catch (Exception e){
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

    public boolean addSupervisor(Long id, Long idSupervisor){
        Optional<RecyclingZoneModel> optionalUpdate = recyclingZoneRepository.findById(id);
        Optional<SupervisorModel> optionalSupervisor = supervisorRepository.findById(idSupervisor);
        if (optionalUpdate.isPresent() && optionalSupervisor.isPresent()) {
            RecyclingZoneModel update = optionalUpdate.get();
            SupervisorModel supervisor = optionalSupervisor.get();
            update.setSupervisor(supervisor);
            recyclingZoneRepository.save(update);
            return true;
        } else {
            return false;
        }
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
    public Optional<RecyclingZoneModel> needsReclassification(Long id, Long SupervisorId, RecyclingZoneModel recyclingZone) {
        Optional<RecyclingZoneModel> optionalUpdate = recyclingZoneRepository.findById(id);
        Optional<SupervisorModel> optionalSupervisor = supervisorRepository.findById(SupervisorId);
        if (optionalUpdate.isEmpty() || optionalSupervisor.isEmpty() || optionalUpdate.get().getSupervisor() == null ||
                !Objects.equals(optionalUpdate.get().getSupervisor().getId(), SupervisorId)) {
            return Optional.empty();
        }
        RecyclingZoneModel update = optionalUpdate.get();
        update.setNeedsReclassification(recyclingZone.isNeedsReclassification());
        return Optional.of(recyclingZoneRepository.save(update));
    }
}