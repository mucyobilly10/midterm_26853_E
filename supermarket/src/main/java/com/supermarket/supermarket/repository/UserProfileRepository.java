package com.supermarket.supermarket.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.supermarket.supermarket.domain.UserProfile;

@Repository
public interface UserProfileRepository extends JpaRepository<UserProfile, UUID> {

    Boolean existsByUserId(UUID userId);
}