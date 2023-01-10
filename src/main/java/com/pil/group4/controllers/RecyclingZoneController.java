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
    public ArrayList<RecyclingZoneModel> getRecyclingZones() {
        return this.recyclingZoneService.getRecyclingZones();
    }

    @GetMapping("/{id}")
    public Optional<RecyclingZoneModel> getRecyclingZoneById(@PathVariable("id") Long idRecyclingZone) {
        return recyclingZoneService.getRecyclingZoneById(idRecyclingZone);
    }

    @PostMapping
    public RecyclingZoneModel saveRecyclingZone(@RequestBody RecyclingZoneModel recyclingZone) {
        return this.recyclingZoneService.saveRecyclingZone(recyclingZone);
    }
    
    @PutMapping("/{id}")
    public RecyclingZoneModel updateRecyclingZoneById(@PathVariable("id") Long id, @RequestBody RecyclingZoneModel recyclingZone) {
        return this.recyclingZoneService.updateRecyclingZoneById(id, recyclingZone);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteOfRecyclingZoneById(@PathVariable("id") Long id) {
        boolean answer = this.recyclingZoneService.deleteOfRecyclingZone(id);
        if (answer) {
            return "The Recycling Zone with id: " + id + ", was removed";
        } else {
            return "The Recycling Zone with id: " + id + ", wasn't removed";
        }
    }
}