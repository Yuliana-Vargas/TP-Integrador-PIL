package com.pil.group4.controllers;

import com.pil.group4.models.RecyclingZoneModel;
import com.pil.group4.services.RecyclingZoneService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RecyclingZoneControllerUnitTest {


    @Mock
    private RecyclingZoneService recyclingZoneService;

    @InjectMocks
    private RecyclingZoneController recyclingZoneController;

    @Test
    public void getRecyclingZonesTest() {
        ArrayList<RecyclingZoneModel> recyclingZones = new ArrayList<>();

        RecyclingZoneModel recyclingZone = new RecyclingZoneModel();
        RecyclingZoneModel recyclingZone2 = new RecyclingZoneModel();
        RecyclingZoneModel recyclingZone3 = new RecyclingZoneModel();

        recyclingZone.setName("Recycling Zone 1");
        recyclingZone2.setName("Recycling Zone 2");
        recyclingZone3.setName("Recycling Zone 3");
        recyclingZone.setNeedsReclassification(false);
        recyclingZone2.setNeedsReclassification(true);
        recyclingZone3.setNeedsReclassification(false);


        recyclingZones.add(recyclingZone);
        recyclingZones.add(recyclingZone2);
        recyclingZones.add(recyclingZone3);

        when(recyclingZoneService.getRecyclingZones()).thenReturn(recyclingZones);
        assertEquals(recyclingZones, recyclingZoneController.getRecyclingZones());
    }

    @Test
    public void getRecyclingZoneByIdTest() {
        RecyclingZoneModel recyclingZone = new RecyclingZoneModel();
        recyclingZone.setId(1L);
        recyclingZone.setName("Recycling Zone 1");
        recyclingZone.setNeedsReclassification(false);

        when(recyclingZoneService.getRecyclingZoneById(recyclingZone.getId())).thenReturn(Optional.of(recyclingZone));
        Optional<RecyclingZoneModel> recyclingZoneById = recyclingZoneService.getRecyclingZoneById(1L);

        assertNotNull(recyclingZoneById);
        assertEquals(recyclingZone.getId(), recyclingZoneById.orElseThrow().getId());
    }

    @Test
    public void saveRecyclingZoneTest() {
        RecyclingZoneModel recyclingZone = new RecyclingZoneModel();
        recyclingZone.setName("Recycling Zone 1");
        recyclingZone.setNeedsReclassification(false);

        when(recyclingZoneService.saveRecyclingZone(recyclingZone)).thenReturn(recyclingZone);
        assertEquals(recyclingZone, recyclingZoneController.saveRecyclingZone(recyclingZone));
    }

    @Test
    public void deleteRecyclingZoneTest() {
        RecyclingZoneModel recyclingZone = new RecyclingZoneModel();
        recyclingZone.setId(2L);

        recyclingZoneService.deleteOfRecyclingZone(recyclingZone.getId());

        assertFalse(recyclingZoneService.getRecyclingZoneById(2L).isPresent());

    }
    
}