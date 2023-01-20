package com.pil.group4.controllers;

import com.pil.group4.models.RecyclingZoneModel;
import com.pil.group4.services.IRecyclingZoneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;
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
    public List<RecyclingZoneModel> getRecyclingZones() {
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
        return this.recyclingZoneService.deleteOfRecyclingZone(id);

    }

    @PutMapping("/{id}/supervisor/{idSupervisor}")
    public String addSupervisor(@PathVariable("id") Long id, @PathVariable("idSupervisor") Long idSupervisor) {
        return this.recyclingZoneService.addSupervisor(id, idSupervisor);
    }

    @DeleteMapping("/{id}/supervisor/{idSupervisor}")
    public String deleteSupervisor(@PathVariable("id") Long id, @PathVariable("idSupervisor") Long idSupervisor) {
        return this.recyclingZoneService.deleteSupervisor(id, idSupervisor);
    }

    @PutMapping("/{id}/supervisor/{idSupervisor}/change-classification-type")
    public Optional<RecyclingZoneModel> changeClassificationType(@PathVariable("id") Long id, @PathVariable("idSupervisor") Long idSupervisor, @RequestBody RecyclingZoneModel recyclingZone) {
        return this.recyclingZoneService.changeClassificationType(id, idSupervisor, recyclingZone);
    }

    @PutMapping("/{id}/supervisor/{idSupervisor}/change-state-of-the-zone")
    public Optional<RecyclingZoneModel> changeStateOfTheZone(@PathVariable("id") Long id, @PathVariable("idSupervisor") Long idSupervisor, @RequestBody RecyclingZoneModel recyclingZone) {
        return this.recyclingZoneService.changeStateOfTheZone(id, idSupervisor, recyclingZone);
    }

    @GetMapping("/supervisor/{idSupervisor}")
    public Optional<RecyclingZoneModel> getRecyclingZonesBySupervisor(@PathVariable("idSupervisor") Long idSupervisor) {
        return this.recyclingZoneService.getRecyclingZoneBySupervisor(idSupervisor);
    }

    @GetMapping("/location/department/{department}")
    public List<RecyclingZoneModel> getRecyclingZonesByDepartment(@PathVariable("department") String department) {
        return this.recyclingZoneService.getRecyclingZonesByDepartment(department);
    }
    
    @PutMapping("/{id}/supervisor/{idSupervisor}/needs-reclassification")
    public Optional<RecyclingZoneModel> needsReclassification(@PathVariable("id") Long id, @PathVariable("idSupervisor") Long idSupervisor, @RequestBody RecyclingZoneModel recyclingZone) {
        return this.recyclingZoneService.changeClassificationType(id, idSupervisor, recyclingZone);
    }

    @GetMapping("recollection-route/x{x}/y{y}")
    public String getRecollectionRoute(@RequestBody List<Integer> recyclingZonesIds, @PathVariable("x") Integer x, @PathVariable("y") Integer y) {
        return this.recyclingZoneService.shortestRoute(recyclingZonesIds, new Point(x, y));
    }

}