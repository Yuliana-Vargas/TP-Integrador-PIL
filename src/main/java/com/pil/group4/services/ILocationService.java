package com.pil.group4.services;

import com.pil.group4.models.LocationModel;

import java.util.List;
import java.util.Optional;

public interface ILocationService {

    Optional<LocationModel> getLocationById(Long idLocation);

    List<LocationModel> getLocations();

    LocationModel saveLocation(LocationModel locationModel);

}
