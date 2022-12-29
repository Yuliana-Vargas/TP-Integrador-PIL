package com.pil.group4.controller;

import com.pil.group4.model.RecyclingZone;
import com.pil.group4.repository.RecyclingZoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecyclingZoneController {

    @Autowired
    private RecyclingZoneRepository recyclingZoneRepository;

    @PostMapping(value = "/savezone")
    public String saveRecyclingZone(@RequestBody RecyclingZone recyclingZone){
        recyclingZoneRepository.save(recyclingZone);
        return "Recycling zone saved";
    }

    @GetMapping(value= "/zones")
    public List<RecyclingZone> getZones(){
        return recyclingZoneRepository.findAll();
    }
    
}
