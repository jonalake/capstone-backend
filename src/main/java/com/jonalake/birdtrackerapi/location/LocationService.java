package com.jonalake.birdtrackerapi.location;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
  @Autowired
  private LocationRepository locationRepository;

  public Iterable<Location> list() {
    return locationRepository.findAll();
  }

  public Optional<Location> findById(Long id) {
    return locationRepository.findById(id);
  }

  public Location create(Location location) {
    return locationRepository.save(location);
  }

  public Optional<Location> update(Location location) {
    Optional<Location> foundLocation = locationRepository.findById(location.getId());

    if (foundLocation.isPresent()) {
      Location updatedLocation = foundLocation.get();
      updatedLocation.setName(location.getName());

      locationRepository.save(updatedLocation);
      return Optional.of(updatedLocation);
    } else {
      return Optional.empty();
    }
  }

  public void deleteById(Long id) {
    locationRepository.deleteById(id);
  }
}