package com.supermarket.supermarket.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.supermarket.supermarket.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    Boolean existsByName(String name);

    List<Product> findByCategory_Id(UUID categoryId);

    Page<Product> findAll(Pageable pageable);

    List<Product> findByNameContaining(String name);
}