package com.pil.group4.services;

import com.pil.group4.models.SupervisorModel;
import com.pil.group4.repositories.SupervisorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class SupervisorService implements ISupervisorService{
    private final SupervisorRepository supervisorRepository;

    @Autowired
    public SupervisorService(SupervisorRepository supervisorRepository) {
        this.supervisorRepository = supervisorRepository;
    }

    @Override
    public ArrayList<SupervisorModel> getSupervisors() {
        return (ArrayList<SupervisorModel>) supervisorRepository.findAll();
    }

    @Override
    public SupervisorModel saveSupervisor(SupervisorModel supervisorModel) {
        return supervisorRepository.save(supervisorModel);
    }

    @Override
    public Optional<SupervisorModel> getSupervisorById(Long idSupervisor) {
        return supervisorRepository.findById(idSupervisor);
    }

    public SupervisorModel updateSupervisorById(SupervisorModel newSupervisorModel, Long id) {
        return supervisorRepository.findById(id)
                .map(supervisor -> {
                    supervisor.setSupervisorName(newSupervisorModel.getSupervisorName());
                    return supervisorRepository.save(supervisor);
                })
                .orElseGet(() -> supervisorRepository.save(newSupervisorModel));
    }

    @Override
    public boolean deleteSupervisor(Long id){
        try{
            supervisorRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}