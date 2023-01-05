package com.pil.group4.controllers;

import com.pil.group4.models.RecyclingZoneModel;
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
        return this.recyclingZoneService.getRecyclingZones();
    }

    @PostMapping
    public RecyclingZoneModel saveRecyclingZone(@RequestBody RecyclingZoneModel recyclingZone){
        return this.recyclingZoneService.saveRecyclingZone(recyclingZone);
    }
    @DeleteMapping(path ="/{id}")
    public String deleteOfRecyclingZoneById(@PathVariable("id") Long id) {
        boolean answer = this.recyclingZoneService.deleteOfRecyclingZone(id);
        if (answer == true) {
            return "The Recycling Zone with id: " + id +", was removed";
        } else {
            return "The Recycling Zone with id: " + id +", wasn't removed";
        }
    }
}