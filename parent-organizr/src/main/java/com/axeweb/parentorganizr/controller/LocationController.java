package com.axeweb.parentorganizr.controller;

import com.axeweb.parentorganizr.controller.exception.LocationNotFoundException;
import com.axeweb.parentorganizr.model.Location;
import com.axeweb.parentorganizr.repository.LocationRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {

    private final LocationRepository locationRepository;

    public LocationController(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @GetMapping("")
    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    @GetMapping("/{id}")
    public Location findById(@PathVariable Integer id) {
        return locationRepository.findById(id).orElseThrow(LocationNotFoundException::new);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public Location create(@RequestBody  @Valid Location location) {
        return locationRepository.save(location);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping("/{id}")
    public Location update(@PathVariable Integer id, @RequestBody @Valid Location location) {
        Location existingLocation = locationRepository.findById(id).orElseThrow(LocationNotFoundException::new);
        existingLocation.setLocationName(location.getLocationName());
        existingLocation.setPlace(location.getPlace());
        existingLocation.setBuilding(location.getBuilding());
        if (location.getRoomNr() != null) {
            existingLocation.setRoomNr(location.getRoomNr());
        }
        if (location.getFloor() != null) {
            existingLocation.setFloor(location.getFloor());
        }
        if (location.getDescription() != null) {
            existingLocation.setDescription(location.getDescription());
        }

        return locationRepository.save(existingLocation);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        locationRepository.deleteById(id);
    }
}
