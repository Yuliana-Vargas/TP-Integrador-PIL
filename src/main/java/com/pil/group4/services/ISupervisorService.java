package com.pil.group4.services;

import com.pil.group4.models.SupervisorModel;

import java.util.ArrayList;
import java.util.Optional;

public interface ISupervisorService {
    ArrayList<SupervisorModel> getSupervisors();

    SupervisorModel saveSupervisor(SupervisorModel supervisorModel);

    Optional<SupervisorModel> getSupervisorById(Long idSupervisor);

    SupervisorModel updateSupervisorById(SupervisorModel newSupervisorModel, Long id);

    String deleteSupervisor(Long id);
}
