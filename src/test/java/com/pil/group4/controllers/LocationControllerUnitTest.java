package com.pil.group4.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.pil.group4.models.LocationModel;
import com.pil.group4.services.LocationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = LocationController.class)
class LocationControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LocationService locationService;

    private List<LocationModel> locationList;

    @BeforeEach
    void setUp() {
        this.locationList = new ArrayList<>();
        this.locationList.add(new LocationModel("Capital", "Centro", "Montevideo",
                123, new Point(-314216005, -641907307)));
        this.locationList.add(new LocationModel("Capital", "Alberdi", "Arturo Orgaz",
                510, new Point(-314033216,-642089571)));
        this.locationList.add(new LocationModel("Capital", "Guemes", "Belgrano",
                783, new Point(-314239776,-641920699)));
    }

    @Test
    void getLocationsTest() throws Exception {
        when(locationService.getLocations()).thenReturn(locationList);

        mockMvc.perform(get("/location"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[2].neighborhood", is("Guemes")));
    }

    @Test
    void getLocationByIdTest() throws Exception {
        when(locationService.getLocationById(1L)).thenReturn(Optional.of(locationList.get(0)));

        mockMvc.perform(get("/location/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.neighborhood", is("Centro")));
    }

    @Test
    void saveLocationTest() throws Exception {
        when(locationService.saveLocation(any(LocationModel.class))).then((invocation) -> invocation.getArgument(0));

        LocationModel locationModel = new LocationModel("Capital", "Centro", "Montevideo",
                124, new Point(-314216005, -641907307));

        mockMvc.perform(post("/location")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(locationModel)))
                        .andExpect(status().isOk());
    }

    @Test
    void deleteLocationTest() throws Exception {
        when(locationService.getLocationById(1L)).thenReturn(Optional.of(locationList.get(0)));

        mockMvc.perform(delete("/location/1"))
                .andExpect(status().isOk());
    }

    @Test
    void updateLocationTest() throws Exception {
        when(locationService.getLocationById(1L)).thenReturn(Optional.of(locationList.get(0)));
        when(locationService.saveLocation(any(LocationModel.class))).then((invocation) -> invocation.getArgument(0));

        LocationModel locationModel = new LocationModel("Capital", "Centro", "Montevideo",
                124, new Point(-314216005, -641907307));

        mockMvc.perform(put("/location/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(locationModel)))
                    .andExpect(status().isOk());
    }

}