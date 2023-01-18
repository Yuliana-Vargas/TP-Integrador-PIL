package com.pil.group4.services;

import com.pil.group4.models.SupervisorModel;

import java.util.ArrayList;
import java.util.Optional;

public interface ISupervisorService {
    ArrayList<SupervisorModel> getSupervisor();
    SupervisorModel saveSupervisor(SupervisorModel supervisorModel);
    Optional<SupervisorModel> getSupervisorById(Long idSupervisor);

}
