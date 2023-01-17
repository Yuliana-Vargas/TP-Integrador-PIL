package com.pil.group4.services;

import com.pil.group4.models.SupervisorModel;
import com.pil.group4.repositories.SupervisorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SupervisorService implements ISupervisorService{
    private final SupervisorRepository supervisorRepository;

    @Autowired
    public SupervisorService(SupervisorRepository supervisorRepository) {
        this.supervisorRepository = supervisorRepository;
    }

    @Override
    public ArrayList<SupervisorModel> getSupervisor() {
        return (ArrayList<SupervisorModel>) supervisorRepository.findAll();
    }

    @Override
    public SupervisorModel saveSupervisor(SupervisorModel supervisorModel) {
        return supervisorRepository.save(supervisorModel);
    }
}