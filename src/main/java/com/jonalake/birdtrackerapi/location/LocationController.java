package com.jonalake.birdtrackerapi.location;

import java.util.Map;
import java.util.HashMap;

import com.jonalake.birdtrackerapi.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RestController
@RequestMapping("locations")
public class LocationController {
  @Autowired
  private LocationService locationService;

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public Map<String, Iterable<Location>> list() {
    Iterable<Location> locations = locationService.list();
    return createHashPlural(locations);
  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Map<String, Location> read(@PathVariable Long id) {
    Location location = locationService
        .findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("No location with that ID"));
    return createHashSingular(location);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Map<String, Location> create(@Validated @RequestBody Location location) {
    Location createdLocation = locationService.create(location);
    return createHashSingular(createdLocation);
  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.CREATED)
  public Map<String, Location> update(@RequestBody Location location, @PathVariable Long id) {
    Location updatedLocation = locationService
        .update(location)
        .orElseThrow(() -> new ResourceNotFoundException("No location with that ID"));

    return createHashSingular(updatedLocation);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    locationService.deleteById(id);
  }

  private Map<String, Location> createHashSingular(Location location) {
    Map<String, Location> response = new HashMap<String, Location>();
    response.put("location", location);

    return response;
  }

  private Map<String, Iterable<Location>> createHashPlural(Iterable<Location> locations) {
    Map<String, Iterable<Location>> response = new HashMap<String, Iterable<Location>>();
    response.put("locations", locations);

    return response;
  }
}