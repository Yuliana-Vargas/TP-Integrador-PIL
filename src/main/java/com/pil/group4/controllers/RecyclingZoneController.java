package com.pil.group4.controllers;

import com.pil.group4.models.RecyclingZoneModel;
import com.pil.group4.repositories.RecyclingZoneRepository;
import com.pil.group4.services.RecyclingZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/recycling-zone")
public class RecyclingZoneController {

    @Autowired
    private RecyclingZoneService recyclingZoneService;
    @Autowired
    private RecyclingZoneRepository recyclingZoneRepository;

    @GetMapping
    public ArrayList<RecyclingZoneModel> getRecyclingZones(){
        return this.recyclingZoneService.getRecyclingZones();
    }

    @PostMapping
    public RecyclingZoneModel saveRecyclingZone(@RequestBody RecyclingZoneModel recyclingZone){
        return this.recyclingZoneService.saveRecyclingZone(recyclingZone);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecyclingZoneModel> updateRecyclingZoneById(@PathVariable("id") long id, @RequestBody RecyclingZoneModel recyclingZoneDetails) {
        Optional<RecyclingZoneModel> optionalUpdate = recyclingZoneRepository.findById(id);
        RecyclingZoneModel update = optionalUpdate.get();
        update.setName(recyclingZoneDetails.getName());
        recyclingZoneRepository.save(update);
        return ResponseEntity.ok(update);
    }
}