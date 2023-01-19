package com.pil.group4.controllers;


import com.pil.group4.models.LocationModel;
import com.pil.group4.services.LocationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LocationControllerTest {

    @Mock
    private LocationService locationService;
    @Test
    void deleteLocationById() {
        LocationModel location = new LocationModel();
        location.setId(1L);

        locationService.deleteLocation(location.getId());

        assertFalse(locationService.getLocationById(1L).isPresent());
    }
}