package com.pil.group4.services;

import com.pil.group4.models.SupervisorModel;
import com.pil.group4.repositories.SupervisorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SupervisorServiceTest {
    @Mock
    private SupervisorRepository supervisorRepository;

    @InjectMocks
    private SupervisorService supervisorService;

    @Test
    void getSupervisorsTest() {
        List<SupervisorModel> supervisors = new ArrayList<>();
        assertTrue(supervisors.isEmpty());
        supervisors.add(new SupervisorModel(1L, "Supervisor1"));
        supervisors.add(new SupervisorModel(2L, "Supervisor2"));
        supervisors.add(new SupervisorModel(3L, "Supervisor3"));

        assertFalse(supervisors.isEmpty());
        when(supervisorRepository.findAll()).thenReturn(supervisors);
        assertEquals(supervisorService.getSupervisors(), supervisors);
    }

    @Test
    void saveSupervisorTest() {
        SupervisorModel supervisorModel = new SupervisorModel(1L, "Supervisor1");
        when(supervisorRepository.save(supervisorModel)).then(invocation -> invocation.getArgument(0));

        SupervisorModel savedSupervisor = supervisorService.saveSupervisor(supervisorModel);
        assertThat(savedSupervisor).isNotNull();
        verify(supervisorRepository).save(any(SupervisorModel.class));
    }

    @Test
    void getSupervisorByIdTest() {
        Long supervisorId = 1L;
        SupervisorModel supervisorModel = new SupervisorModel(1L, "Supervisor1");

        when(supervisorRepository.findById(supervisorId)).thenReturn(Optional.of(supervisorModel));

        Optional<SupervisorModel> supervisorExpected = supervisorService.getSupervisorById(supervisorId);
        assertThat(supervisorExpected).isNotNull();
        assertTrue(supervisorExpected.isPresent());
        assertEquals("Supervisor1", supervisorExpected.get().getSupervisorName());
        verify(supervisorRepository).findById(any(Long.class));
    }

    @Test
    void updateSupervisorByIdTest() {
        SupervisorModel supervisorModel = new SupervisorModel(1L, "Supervisor1");
        when(supervisorRepository.save(supervisorModel)).thenReturn(supervisorModel);
        SupervisorModel supervisorExpected = supervisorService.updateSupervisorById(supervisorModel, supervisorModel.getId());
        assertThat(supervisorExpected).isNotNull();
        verify(supervisorRepository).save(any(SupervisorModel.class));
    }

    @Test
    void deleteSupervisorTest() {
        Long supervisorId = 1L;

        supervisorService.deleteSupervisor(supervisorId);
        supervisorService.deleteSupervisor(supervisorId);
        verify(supervisorRepository, times(2)).deleteById(supervisorId);
    }
}