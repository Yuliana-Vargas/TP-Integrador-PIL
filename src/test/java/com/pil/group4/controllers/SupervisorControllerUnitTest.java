package com.pil.group4.controllers;

import com.pil.group4.models.SupervisorModel;
import com.pil.group4.services.SupervisorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SupervisorControllerUnitTest {

    @Mock
    private SupervisorService supervisorService;

    @InjectMocks
    private SupervisorController supervisorController;

    @Test
    public void updateSupervisorIdTest() {
        SupervisorModel supervisorModel = new SupervisorModel();
        supervisorModel.setId(1L);
        supervisorModel.setSupervisorName("Supervisor 1");

        SupervisorModel supervisorModel2 = new SupervisorModel();
        supervisorModel.setId(1L);
        supervisorModel.setSupervisorName("Supervisor 2");

        when(supervisorService.updateSupervisorById(supervisorModel2, supervisorModel.getId())).thenReturn(supervisorModel2);
        assertEquals(supervisorModel2, supervisorController.updateSupervisorById(supervisorModel2, supervisorModel.getId()));
    }

    @Test
    public void deleteSupervisorTest() {
        SupervisorModel supervisorModel = new SupervisorModel();
        supervisorModel.setId(1L);

        supervisorService.deleteSupervisor(supervisorModel.getId());

        assertFalse(supervisorService.getSupervisorById(1L).isPresent());

    }

}
