package com.supermarket.supermarket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.supermarket.supermarket.domain.Location;
import com.supermarket.supermarket.domain.User;
import com.supermarket.supermarket.service.LocationService;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveLocation(@RequestBody Location location, @RequestParam(required = false) String parentId) {

        String response = locationService.saveLocation(location, parentId);

        if (response.equals("Location saved successfully.")) {
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAllLocations() {

        List<Location> locations = locationService.getAllLocations();

        if (locations.isEmpty()) {
            return new ResponseEntity<>("No locations found.", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(locations, HttpStatus.OK);
        }
    }

    @GetMapping(value = "/users-by-province", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUsersByProvince(
            @RequestParam(required = false) String provinceCode,
            @RequestParam(required = false) String provinceName) {

        List<User> users = locationService.getUsersByProvince(provinceCode, provinceName);

        if (users == null || users.isEmpty()) {
            return new ResponseEntity<>("No users found for this province.", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
    }
}