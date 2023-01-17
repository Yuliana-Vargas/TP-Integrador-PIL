package com.pil.group4.services;

import com.pil.group4.models.SupervisorModel;

import java.util.ArrayList;

public interface ISupervisorService {
    ArrayList<SupervisorModel> getSupervisor();
    SupervisorModel saveSupervisor(SupervisorModel supervisorModel);
}
