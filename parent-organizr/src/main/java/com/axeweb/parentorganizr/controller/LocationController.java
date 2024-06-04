package com.axeweb.parentorganizr.controller;

import com.axeweb.parentorganizr.model.Location;
import com.axeweb.parentorganizr.repository.LocationRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
