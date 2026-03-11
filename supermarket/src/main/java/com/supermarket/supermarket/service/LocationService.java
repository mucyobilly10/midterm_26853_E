package com.supermarket.supermarket.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supermarket.supermarket.domain.ELocationType;
import com.supermarket.supermarket.domain.Location;
import com.supermarket.supermarket.domain.User;
import com.supermarket.supermarket.repository.LocationRepository;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public String saveLocation(Location location, String parentId) {

        if (parentId != null) {
            Location parentLocation = locationRepository.findById(UUID.fromString(parentId)).orElse(null);

            if (parentLocation == null) {
                return "Parent location not found.";
            } else {
                location.setParent(parentLocation);
            }
        }

        if (!location.getType().equals(ELocationType.PROVINCE) && parentId == null) {
            return "Parent location is required for non-province locations.";
        }

        Boolean locationExists = locationRepository.existsByCode(location.getCode());
        if (locationExists) {
            return "Location with this code already exists.";
        }

        locationRepository.save(location);
        return "Location saved successfully.";
    }

    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    public List<User> getUsersByProvince(String provinceCode, String provinceName) {
        return locationRepository.findUsersByProvinceCodeOrName(provinceCode, provinceName);
    }
}