package com.pil.group4.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pil.group4.models.SupervisorModel;
import com.pil.group4.services.SupervisorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebMvcTest(controllers = SupervisorController.class)
public class SupervisorControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SupervisorService supervisorService;

    private List<SupervisorModel> supervisorList;

    @BeforeEach
    void setUp() {
        this.supervisorList = new ArrayList<>();
        this.supervisorList.add(new SupervisorModel(1L, "Supervisor1"));
        this.supervisorList.add(new SupervisorModel(2L, "Supervisor2"));
        this.supervisorList.add(new SupervisorModel(3L, "Supervisor3"));

    }

    @Test
    void getSupervisorsTest() throws Exception {
        when(supervisorService.getSupervisors()).thenReturn((ArrayList<SupervisorModel>) supervisorList);

        mockMvc.perform(get("/supervisor"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].supervisorName", is("Supervisor3")));
    }

    @Test
    void getSupervisorByIdTest() throws Exception {
        final Long supervisorId = 1L;
        final SupervisorModel supervisorModel = new SupervisorModel(1L, "Supervisor1");

        when(supervisorService.getSupervisorById(supervisorId)).thenReturn(Optional.of(supervisorModel));

        mockMvc.perform(get("/supervisor/{id}", supervisorId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.supervisorName", is(supervisorModel.getSupervisorName())));
    }

    @Test
    void saveSupervisorTest() throws Exception {
        when(supervisorService.saveSupervisor(any(SupervisorModel.class))).then((invocation) -> invocation.getArgument(0));

        SupervisorModel supervisorModel = new SupervisorModel(null, "Supervisor1");

        mockMvc.perform(post("/supervisor")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(supervisorModel)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.supervisorName", is(supervisorModel.getSupervisorName())));
    }

    @Test
    void updateSupervisorByIdTest() throws Exception {
        Long supervisorId = 1L;
        SupervisorModel supervisorModel = new SupervisorModel(supervisorId, "Supervisor1");
        when(supervisorService.getSupervisorById(supervisorId)).thenReturn(Optional.of(supervisorModel));
        when(supervisorService.updateSupervisorById(any(SupervisorModel.class), any(Long.class))).then((invocation) -> invocation.getArgument(0));

        mockMvc.perform(put("/supervisor/{id}", supervisorModel.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(supervisorModel)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.supervisorName", is(supervisorModel.getSupervisorName())));
    }

    @Test
    void deleteSupervisorByIdTest() throws Exception {
        Long supervisorId = 1L;
        SupervisorModel supervisorModel = new SupervisorModel(supervisorId, "Supervisor1");
        when(supervisorService.getSupervisorById(supervisorModel.getId())).thenReturn(Optional.of(supervisorModel));

        mockMvc.perform(delete("/supervisor/{id}", supervisorModel.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
