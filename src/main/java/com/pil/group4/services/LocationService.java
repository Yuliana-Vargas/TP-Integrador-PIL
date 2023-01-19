package com.pil.group4.services;

import com.pil.group4.models.LocationModel;
import com.pil.group4.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService implements ILocationService {

    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public Optional<LocationModel> getLocationById(Long idLocation) {
        return this.locationRepository.findById(idLocation);
    }

    @Override
    public List<LocationModel> getLocations() {
        return this.locationRepository.findAll();
    }

    @Override
    public LocationModel saveLocation(LocationModel locationModel) {
        return this.locationRepository.save(locationModel);
    }

    @Override
    public boolean deleteLocation(Long id) {
        try {
            locationRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
