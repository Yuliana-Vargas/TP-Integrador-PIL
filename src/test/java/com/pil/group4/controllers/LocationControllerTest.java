package com.pil.group4.controllers;


import com.pil.group4.models.LocationModel;
import com.pil.group4.services.LocationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationControllerTest {

    @Mock
    private LocationService locationService;

    @InjectMocks
    private LocationController locationController;

    @Test
    public void updateLocationIdTest() {
        LocationModel locationModel = new LocationModel();
        locationModel.setId(1L);
        locationModel.setAddress("Address 123");

        LocationModel locationModel2 = new LocationModel();
        locationModel2.setId(1L);
        locationModel2.setAddress("Address 456");

        when(locationController.updateLocationById(locationModel2, locationModel.getId())).thenReturn(locationModel2);
        assertEquals(locationModel2, locationController.updateLocationById(locationModel2, locationModel.getId()));
    }
    @Test
    void deleteLocationById() {
        LocationModel location = new LocationModel();
        location.setId(1L);

        locationService.deleteLocation(location.getId());

        assertFalse(locationService.getLocationById(1L).isPresent());
    }
}