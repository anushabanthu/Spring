package com.cjss.employee.controller;

import com.cjss.employee.entity.LocationEntity;
import com.cjss.employee.model.Location;
import com.cjss.employee.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LocationController {
    @Autowired
    LocationService locationService;

    @PostMapping("/get-loc-details-by-id")
    public Location getLocDetailsById(Integer id){
        System.out.println("LocationController: getLocations");
        return locationService.getLocDetailsById(id);
    }
    @PostMapping("/add-location")
    public LocationEntity addLocation(@RequestBody Location location){
        System.out.println("LocationController: addLocations");
        return locationService.addLocDetails(location);
    }
    @DeleteMapping("/delete-location/{id}")
    public void deleteLocation(@PathVariable int id){
        System.out.println("LocationController: deleteLocation");
        locationService.deleteLocationById(id);
    }
}



