package com.Droame.Services;

import java.util.List;

import com.Droame.Exception.NotFoundException;
import com.Droame.entities.Location;


public interface LocationService {
    List<Location> getAllLocations();
    Location getLocationById(Long id) throws NotFoundException;
    Location createLocation(Location location);
    Location updateLocation(Long id, Location location) throws NotFoundException;
    void deleteLocation(Long id) throws NotFoundException;
}


