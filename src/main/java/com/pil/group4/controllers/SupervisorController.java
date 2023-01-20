package com.pil.group4.controllers;

import com.pil.group4.models.SupervisorModel;
import com.pil.group4.services.ISupervisorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/supervisor")
public class SupervisorController {
    private final ISupervisorService supervisorService;

    @Autowired
    public SupervisorController(ISupervisorService supervisorService) {
        this.supervisorService = supervisorService;
    }

    @GetMapping
    public ArrayList<SupervisorModel> getSupervisors() {
        return supervisorService.getSupervisors();
    }

    @PostMapping
    public SupervisorModel saveSupervisor(@RequestBody SupervisorModel supervisorModel) {
        return supervisorService.saveSupervisor(supervisorModel);
    }

    @GetMapping("/{id}")
    public Optional<SupervisorModel> getSupervisorById(@PathVariable("id") Long idSupervisor) {
        return supervisorService.getSupervisorById(idSupervisor);
    }

    @PutMapping("/{id}")
    public SupervisorModel updateSupervisorById(@RequestBody SupervisorModel newSupervisorModel, @PathVariable Long id) {
        return supervisorService.updateSupervisorById(newSupervisorModel, id);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteSupervisorById(@PathVariable("id") Long id) {
        return this.supervisorService.deleteSupervisor(id);
    }

}