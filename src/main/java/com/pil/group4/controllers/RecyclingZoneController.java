package com.pil.group4.controllers;

import com.pil.group4.models.RecyclingZoneModel;
import com.pil.group4.services.IRecyclingZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/recycling-zone")
public class RecyclingZoneController {

    private final IRecyclingZoneService recyclingZoneService;

    @Autowired
    public RecyclingZoneController(IRecyclingZoneService recyclingZoneService) {
        this.recyclingZoneService = recyclingZoneService;
    }

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

    @PutMapping("/{id}/supervisor/{idSupervisor}")
    public String addSupervisor(@PathVariable("id") Long id, @PathVariable("idSupervisor") Long idSupervisor) {
        return this.recyclingZoneService.addSupervisor(id, idSupervisor) ? "The Supervisor with id: " + idSupervisor +
                ", was added to the Recycling Zone with id: " + id : "The Supervisor with id: " + idSupervisor +
                ", wasn't added to the Recycling Zone with id: " + id;
    }

    @PutMapping("/{id}/supervisor/{idSupervisor}/change-classification-type")
    public Optional<RecyclingZoneModel> changeClassificationType(@PathVariable("id") Long id, @PathVariable("idSupervisor") Long idSupervisor, @RequestBody RecyclingZoneModel recyclingZone) {
        return this.recyclingZoneService.changeClassificationType(id, idSupervisor, recyclingZone);
    }

    @PutMapping("/{id}/supervisor/{idSupervisor}/change-state-of-the-zone")
    public Optional<RecyclingZoneModel> changeStateOfTheZone(@PathVariable("id") Long id, @PathVariable("idSupervisor") Long idSupervisor, @RequestBody RecyclingZoneModel recyclingZone) {
        return this.recyclingZoneService.changeStateOfTheZone(id, idSupervisor, recyclingZone);
    }

}