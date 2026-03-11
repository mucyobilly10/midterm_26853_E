package com.supermarket.supermarket.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.supermarket.supermarket.domain.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, UUID> {

    Boolean existsByContactEmail(String contactEmail);
}