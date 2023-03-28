package com.Droame.ServicesImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Droame.Exception.NotFoundException;
import com.Droame.Repositrory.LocationRepository;
import com.Droame.Services.LocationService;
import com.Droame.entities.Location;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    @Override
    public Location getLocationById(Long id) throws NotFoundException {
        return locationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Location not found with id: " + id));
    }

    @Override
    public Location createLocation(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public Location updateLocation(Long id, Location location) throws NotFoundException {
        Optional<Location> existingLocation = locationRepository.findById(id);
        if (existingLocation.isEmpty()) {
            throw new NotFoundException("Location not found with id: " + id);
        }
        existingLocation.get().setName(location.getName());
        return locationRepository.save(existingLocation.get());
    }

    @Override
    public void deleteLocation(Long id) throws NotFoundException {
        if (!locationRepository.existsById(id)) {
            throw new NotFoundException("Location not found with id: " + id);
        }
        locationRepository.deleteById(id);
    }
}




