package com.pil.group4.controllers;

import com.pil.group4.models.RecyclingZoneModel;
import com.pil.group4.services.RecyclingZoneService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RecyclingZoneControllerUnitTest {

    @Mock
    private RecyclingZoneService recyclingZoneService;

    @InjectMocks
    private RecyclingZoneController recyclingZoneController;

    @Test
    public void getRecyclingZones(){
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
    public void saveRecyclingZone(){
        RecyclingZoneModel recyclingZone = new RecyclingZoneModel();
        recyclingZone.setName("Recycling Zone 1");
        recyclingZone.setNeedsReclassification(false);

        when(recyclingZoneService.saveRecyclingZone(recyclingZone)).thenReturn(recyclingZone);
        assertEquals(recyclingZone, recyclingZoneController.saveRecyclingZone(recyclingZone));
    }

    @Test
    public void updateRecyclingZoneByIdTest() {
        RecyclingZoneModel recyclingZone = new RecyclingZoneModel();
        recyclingZone.setId(1L);
        recyclingZone.setName("Recycling Zone 1");
        recyclingZone.setNeedsReclassification(false);

        RecyclingZoneModel recyclingZone2 = new RecyclingZoneModel();
        recyclingZone2.setId(1L);
        recyclingZone2.setName("Recycling Zone 2");
        recyclingZone2.setNeedsReclassification(false);

        when(recyclingZoneService.updateRecyclingZoneById(recyclingZone.getId(), recyclingZone2)).thenReturn(recyclingZone2);
        assertEquals(recyclingZone2, recyclingZoneController.updateRecyclingZoneById(recyclingZone.getId(), recyclingZone2));
    }
}