package com.pil.group4.controllers;

import com.pil.group4.models.LocationModel;
import com.pil.group4.services.ILocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public Optional<LocationModel> getLocationById(@PathVariable("id") Long idLocation) {
        return this.locationService.getLocationById(idLocation);
    }

    @PostMapping
    public LocationModel saveLocation(@RequestBody LocationModel locationModel) {
        return this.locationService.saveLocation(locationModel);
    }

    @PutMapping("/{id}")
    public LocationModel updateLocationById(@RequestBody LocationModel locationModel, @PathVariable Long id) {
        return locationService.updateLocationById(id, locationModel);
    }

    @DeleteMapping(path = "/{id}")
    public String deleteLocationById(@PathVariable("id") Long id) {
        boolean answer = locationService.deleteLocation(id);
        if (answer) {
            return "The Location with the id: " + id + ", was removed";
        } else {
            return "The Location with the id: " + id + ", wasn't removed";
        }
    }

}
