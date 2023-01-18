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
        return supervisorService.getSupervisor();
    }

    @PostMapping
    public SupervisorModel saveSupervisor(@RequestBody SupervisorModel supervisorModel) {
        return supervisorService.saveSupervisor(supervisorModel);
    }
    @GetMapping("/{id}")
    public Optional<SupervisorModel> getSupervisorById(@PathVariable("id") Long idSupervisor) {
        return supervisorService.getSupervisorById(idSupervisor);
    }
    @DeleteMapping(path = "/{id}")
    public String deleteSupervisorById(@PathVariable("id") Long id) {
        boolean answer = this.supervisorService.deleteSupervisor(id);
        if (answer) {
            return "The Supervisor with the id: " + id + ", was removed";
        } else {
            return "The Supervisor with the id: " + id + ", wasn't removed";
        }
    }

}