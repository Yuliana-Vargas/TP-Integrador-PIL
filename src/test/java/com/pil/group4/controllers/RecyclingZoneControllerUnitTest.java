package com.pil.group4.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pil.group4.models.RecyclingZoneModel;
import com.pil.group4.models.SupervisorModel;
import com.pil.group4.services.RecyclingZoneService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.pil.group4.models.ClassificationType.*;
import static com.pil.group4.models.OccupationCapacity.*;
import static com.pil.group4.models.StateOfTheZone.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = RecyclingZoneController.class)
public class RecyclingZoneControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private RecyclingZoneService recyclingZoneService;

    private List<RecyclingZoneModel> recyclingZoneList;

    @BeforeEach
    void setUp() {
        this.recyclingZoneList = new ArrayList<>();
        this.recyclingZoneList.add(new RecyclingZoneModel(1L, "RecZone1", FULL, INACCESSIBLE, NON_RECYCLABLE_GARBAGE_DISPOSAL, false));
        this.recyclingZoneList.add(new RecyclingZoneModel(2L, "RecZone2", EMPTY, AVAILABLE, BATTERY_DISPOSAL, false));
        this.recyclingZoneList.add(new RecyclingZoneModel(3L, "RecZone3", EXCEEDED, DAMAGED, GLASS_DISPOSAL, true));
    }

    @Test
    void getRecyclingZonesTest() throws Exception {
        when(recyclingZoneService.getRecyclingZones()).thenReturn(recyclingZoneList);

        mockMvc.perform(get("/recycling-zone"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].name", is("RecZone3")));
    }

    @Test
    void getRecyclingZoneByIdTest() throws Exception {
        final Long recZone1 = 1L;
        final RecyclingZoneModel recyclingZoneModel = new RecyclingZoneModel(1L, "RecZone1", FULL, AVAILABLE, NON_RECYCLABLE_GARBAGE_DISPOSAL, false);

        when(recyclingZoneService.getRecyclingZoneById(recZone1)).thenReturn(Optional.of(recyclingZoneModel));

        mockMvc.perform(get("/recycling-zone/{id}", recZone1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is(recyclingZoneModel.getName())));
    }

    @Test
    void saveRecyclingZoneTest() throws Exception {
        when(recyclingZoneService.saveRecyclingZone(any(RecyclingZoneModel.class))).then((invocation) -> invocation.getArgument(0));

        RecyclingZoneModel recyclingZoneModel = new RecyclingZoneModel(null, "RecZone1", FULL, AVAILABLE, NON_RECYCLABLE_GARBAGE_DISPOSAL, false);

        mockMvc.perform(post("/recycling-zone")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(recyclingZoneModel)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(recyclingZoneModel.getName())));
    }

    @Test
    void updateRecyclingZoneTest() throws Exception {
        Long recZone1 = 1L;
        RecyclingZoneModel recyclingZoneModel = new RecyclingZoneModel(recZone1, "RecZone1", FULL, AVAILABLE, NON_RECYCLABLE_GARBAGE_DISPOSAL, false);
        when(recyclingZoneService.getRecyclingZoneById(recZone1)).thenReturn(Optional.of(recyclingZoneModel));
        when(recyclingZoneService.updateRecyclingZoneById(any(RecyclingZoneModel.class), any(Long.class))).then((invocation) -> invocation.getArgument(0));

        mockMvc.perform(put("/recycling-zone/{id}", recyclingZoneModel.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(recyclingZoneModel)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(recyclingZoneModel.getName())));
    }

    @Test
    void deleteRecyclingZoneByIdTest() throws Exception {
        Long recZone1 = 1L;
        RecyclingZoneModel recyclingZoneModel = new RecyclingZoneModel(recZone1, "RecZone1", FULL, AVAILABLE, NON_RECYCLABLE_GARBAGE_DISPOSAL, false);
        when(recyclingZoneService.getRecyclingZoneById(recyclingZoneModel.getId())).thenReturn(Optional.of(recyclingZoneModel));

        mockMvc.perform(delete("/recycling-zone/{id}", recyclingZoneModel.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getRecyclingZoneBySupervisorTest() throws Exception {
        Long recZone1 = 1L;
        SupervisorModel supervisorModel = new SupervisorModel(1L, "Juan");
        RecyclingZoneModel recyclingZoneModel = new RecyclingZoneModel(1L, "RecZone1", FULL, AVAILABLE, NON_RECYCLABLE_GARBAGE_DISPOSAL, false, supervisorModel);
        when(recyclingZoneService.getRecyclingZoneBySupervisor(recZone1)).thenReturn(Optional.of(recyclingZoneModel));

        mockMvc.perform(get("/recycling-zone/supervisor/{id}", recZone1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.name", is(recyclingZoneModel.getName())));
    }

}