package com.pil.group4.services;

import com.pil.group4.models.LocationModel;
import com.pil.group4.repositories.LocationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LocationServiceTest {

    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private LocationService locationService;

    @Test
    void getLocationsTest() {
        List<LocationModel> locations = new ArrayList<>();
        assertTrue(locations.isEmpty());
        locations.add(new LocationModel("Capital", "Centro", "Montevideo",
                123, new Point(-314216005, -641907307)));
        locations.add(new LocationModel("Capital", "Alberdi", "Arturo Orgaz",
                510, new Point(-314033216, -642089571)));
        locations.add(new LocationModel("Capital", "Guemes", "Belgrano",
                783, new Point(-314239776, -641920699)));

        assertFalse(locations.isEmpty());
        when(locationRepository.findAll()).thenReturn(locations);
        assertEquals(locationService.getLocations(), locations);
    }

    @Test
    void getLocationByIdTest() {
        Long id = 1L;
        LocationModel location = new LocationModel("Capital", "Centro", "Montevideo",
                123, new Point(-314216005, -641907307));

        when(locationRepository.findById(id)).thenReturn(Optional.of(location));

        Optional<LocationModel> locationFound = locationService.getLocationById(id);
        assertTrue(locationFound.isPresent());
        assertThat(locationFound).isNotNull();
        assertEquals(locationFound.get(), location);
    }

    @Test
    void saveLocationTest(){
        LocationModel locationModel = new LocationModel("Capital", "Centro", "Montevideo",
                123, new Point(-314216005, -641907307));

        when(locationRepository.save(any(LocationModel.class))).thenReturn(locationModel);

        LocationModel savedLocation = locationService.saveLocation(locationModel);
        assertThat(savedLocation).isNotNull();
        verify(locationRepository).save(any(LocationModel.class));
    }

    @Test
    public void updateLocationByIdTest(){
        Long id = 1L;
        LocationModel locationModel = new LocationModel("Capital", "Centro", "Montevideo",
                123, new Point(-314216005, -641907307));
        locationModel.setId(id);

        when(locationRepository.findById(id)).thenReturn(Optional.of(locationModel));
        when(locationRepository.save(any(LocationModel.class))).thenReturn(locationModel);

        LocationModel updatedLocation = locationService.updateLocationById(id, locationModel);
        assertThat(updatedLocation).isNotNull();
        verify(locationRepository).save(any(LocationModel.class));
    }

    @Test
    public void deleteLocationByIdTest(){
        Long locationId = 1L;

        locationService.deleteLocation(locationId);
        locationService.deleteLocation(locationId);

        verify(locationRepository, times(2)).deleteById(locationId);

    }

}