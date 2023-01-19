package com.pil.group4.services;

import com.pil.group4.models.LocationModel;

import java.util.List;

public interface ILocationService {

    LocationModel getLocationById(Long idLocation);

    List<LocationModel> getLocations();

}
