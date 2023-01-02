package com.pil.group4.controllers;

import com.pil.group4.models.RecyclingZoneModel;
import com.pil.group4.repositories.RecyclingZoneRepository;
import com.pil.group4.servicies.RecyclingZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/recycling-zone")
public class RecyclingZoneController {

    @Autowired
    private RecyclingZoneService recyclingZoneService;

    @GetMapping
    public ArrayList<RecyclingZoneModel> getRecyclingZones(){
        return recyclingZoneService.getRecyclingZones();
    }

    @PostMapping
    public RecyclingZoneModel saveRecyclingZone(RecyclingZoneModel recyclingZone){
        return recyclingZoneService.saveRecyclingZone(recyclingZone);
    }

}