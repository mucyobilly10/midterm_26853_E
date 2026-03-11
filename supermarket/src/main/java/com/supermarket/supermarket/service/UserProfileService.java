package com.supermarket.supermarket.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.supermarket.supermarket.domain.User;
import com.supermarket.supermarket.domain.UserProfile;
import com.supermarket.supermarket.repository.UserProfileRepository;
import com.supermarket.supermarket.repository.UserRepository;

@Service
public class UserProfileService {

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private UserRepository userRepository;

    public String saveUserProfile(UserProfile userProfile, String userId) {

        User user = userRepository.findById(UUID.fromString(userId)).orElse(null);
        if (user == null) {
            return "User not found.";
        }

        Boolean profileExists = userProfileRepository.existsByUserId(UUID.fromString(userId));
        if (profileExists) {
            return "Profile for this user already exists.";
        }

        userProfile.setUser(user);
        userProfileRepository.save(userProfile);
        return "User profile saved successfully.";
    }
}