package com.supermarket.supermarket.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.supermarket.supermarket.domain.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

    Boolean existsByName(String name);
}
