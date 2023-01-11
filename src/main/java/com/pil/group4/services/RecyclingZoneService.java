package com.pil.group4.services;

import com.pil.group4.models.RecyclingZoneModel;
import com.pil.group4.repositories.RecyclingZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class RecyclingZoneService implements IRecyclingZoneService {
    private final RecyclingZoneRepository recyclingZoneRepository;

    @Autowired
    public RecyclingZoneService(RecyclingZoneRepository recyclingZoneRepository) {
        this.recyclingZoneRepository = recyclingZoneRepository;
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
}