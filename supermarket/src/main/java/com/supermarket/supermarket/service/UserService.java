package com.supermarket.supermarket.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supermarket.supermarket.domain.Location;
import com.supermarket.supermarket.domain.User;
import com.supermarket.supermarket.repository.LocationRepository;
import com.supermarket.supermarket.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LocationRepository locationRepository;

    public String saveUser(User user, String villageCode, String villageName) {

        Boolean userExists = userRepository.existsByEmail(user.getEmail());
        if (userExists) {
            return "User with this email already exists.";
        }

        Location village = locationRepository.findVillageByCodeOrName(villageCode, villageName);
        if (village == null) {
            return "Village not found. Please provide a valid village name or code.";
        }

        user.setVillage(village);
        userRepository.save(user);
        return "User saved successfully.";
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> searchUserByName(String fullName) {
        List<User> users = userRepository.findByFullNameContaining(fullName);
        if (users.isEmpty()) {
            return null;
        }
        return users;
    }
}