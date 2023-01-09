package com.pil.group4.controllers;

import com.pil.group4.models.RecyclingZoneModel;
import com.pil.group4.services.RecyclingZoneService;
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
        return this.recyclingZoneService.getRecyclingZones();
    }

    @PostMapping
    public RecyclingZoneModel saveRecyclingZone(@RequestBody RecyclingZoneModel recyclingZone){
        return this.recyclingZoneService.saveRecyclingZone(recyclingZone);
    }

}