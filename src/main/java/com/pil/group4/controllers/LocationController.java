package com.pil.group4.controllers;

import com.pil.group4.models.LocationModel;
import com.pil.group4.services.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/location")
public class LocationController {

    private final ILocationService locationService;

    @Autowired
    public LocationController(ILocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public List<LocationModel> getLocations() {
        return this.locationService.getLocations();
    }

    @GetMapping("/{id}")
    public LocationModel getLocationById(@PathVariable("id") Long idLocation) {
        return this.locationService.getLocationById(idLocation);
    }

}
