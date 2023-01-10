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
public class RecyclingZoneService {

    @Autowired
    RecyclingZoneRepository recyclingZoneRepository;

    public ArrayList<RecyclingZoneModel> getRecyclingZones(){
        return (ArrayList<RecyclingZoneModel>) recyclingZoneRepository.findAll();
    }

    public Optional<RecyclingZoneModel> getRecyclingZoneById(Long idRecyclingZone){
        return recyclingZoneRepository.findById(idRecyclingZone);
    }

    public RecyclingZoneModel saveRecyclingZone(RecyclingZoneModel recyclingZoneModel){
        return  recyclingZoneRepository.save(recyclingZoneModel);
    }

    public boolean deleteOfRecyclingZone(Long id){
        try{
            recyclingZoneRepository.deleteById(id);
            return true;
        } catch(Exception e){
            return false;
        }
    }


    public RecyclingZoneModel updateRecyclingZoneById(@PathVariable("id") long id, @RequestBody RecyclingZoneModel recyclingZoneDetails) {
        Optional<RecyclingZoneModel> optionalUpdate = recyclingZoneRepository.findById(id);
        RecyclingZoneModel update = optionalUpdate.get();
        update.setName(recyclingZoneDetails.getName());
        return recyclingZoneRepository.save(update);
    }

  
}