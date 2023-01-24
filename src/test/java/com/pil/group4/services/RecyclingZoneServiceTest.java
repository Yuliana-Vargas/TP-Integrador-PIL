package com.pil.group4.services;

import com.pil.group4.models.*;
import com.pil.group4.repositories.RecyclingZoneRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.pil.group4.models.ClassificationType.*;
import static com.pil.group4.models.OccupationCapacity.*;
import static com.pil.group4.models.StateOfTheZone.*;
import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecyclingZoneServiceTest {

    @Mock
    private RecyclingZoneRepository recyclingZoneRepository;

    @InjectMocks
    private RecyclingZoneService recyclingZoneService;

    @Test
    void getRecyclingZonesTest() {
        List<RecyclingZoneModel> recyclingZones = new ArrayList<>();
        assertTrue(recyclingZones.isEmpty());
        recyclingZones.add(new RecyclingZoneModel(1L, "RecZone1", HALF_FULL, DAMAGED, GLASS_DISPOSAL,false));
        recyclingZones.add(new RecyclingZoneModel(2L, "RecZone2",FULL,INACCESSIBLE,BATTERY_DISPOSAL,false));
        recyclingZones.add(new RecyclingZoneModel(3L, "RecZone3",EMPTY,AVAILABLE,NON_RECYCLABLE_GARBAGE_DISPOSAL,true));

        assertFalse(recyclingZones.isEmpty());
        when(recyclingZoneRepository.findAll()).thenReturn(recyclingZones);
        assertEquals(recyclingZoneService.getRecyclingZones(), recyclingZones);
    }

    @Test
    void getRecyclingZoneByIdTest() {
        Long recZone = 1L;
        RecyclingZoneModel recyclingZoneModel = new RecyclingZoneModel(recZone, "RecZone1", HALF_FULL, DAMAGED, GLASS_DISPOSAL,false);

        when(recyclingZoneRepository.findById(recZone)).thenReturn(Optional.of(recyclingZoneModel));

        Optional<RecyclingZoneModel> recZoneExpected = recyclingZoneService.getRecyclingZoneById(recZone);
        assertThat(recZoneExpected).isNotNull();
        assertTrue(recZoneExpected.isPresent());
        assertEquals("RecZone1",recZoneExpected.get().getName());
        verify(recyclingZoneRepository).findById(any(Long.class));
    }

    @Test
    void saveRecyclingZoneTest() {
        RecyclingZoneModel recyclingZoneModel = new RecyclingZoneModel(1L, "RecZone1", HALF_FULL, DAMAGED, GLASS_DISPOSAL,false);
        when(recyclingZoneRepository.save(recyclingZoneModel)).then(invocation -> invocation.getArgument(0));

        RecyclingZoneModel savedRecyclingZone = recyclingZoneService.saveRecyclingZone(recyclingZoneModel);
        assertThat(savedRecyclingZone).isNotNull();
        verify(recyclingZoneRepository).save(any(RecyclingZoneModel.class));
    }

    @Test
    void deleteRecyclingZoneTest() {
        Long recZone = 1L;

        recyclingZoneService.deleteOfRecyclingZone(recZone);
        recyclingZoneService.deleteOfRecyclingZone(recZone);
        verify(recyclingZoneRepository, times(2)).deleteById(recZone);
    }

    @Test
    void updateRecyclingZoneByIdTest() {
        RecyclingZoneModel recyclingZoneModel = new RecyclingZoneModel(1L, "RecZone1", HALF_FULL, DAMAGED, GLASS_DISPOSAL,false);
        when(recyclingZoneRepository.save(recyclingZoneModel)).thenReturn(recyclingZoneModel);
        RecyclingZoneModel recZoneExpected = recyclingZoneService.updateRecyclingZoneById(recyclingZoneModel,recyclingZoneModel.getId());
        assertThat(recZoneExpected).isNotNull();
        verify(recyclingZoneRepository).save(any(RecyclingZoneModel.class));
    }
}
