package com.supermarket.supermarket.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.supermarket.supermarket.domain.ELocationType;
import com.supermarket.supermarket.domain.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, UUID> {

    Boolean existsByCode(String code);

    List<Location> findByType(ELocationType type);

    List<Location> findByNameAndType(String name, ELocationType type);

    @Query("SELECT l FROM Location l WHERE (l.code = :code OR l.name = :name) AND l.type = 'VILLAGE'")
    Location findVillageByCodeOrName(
        @Param("code") String code,
        @Param("name") String name
    );

    @Query("SELECT u FROM User u WHERE u.village.parent.parent.parent.parent.code = :provinceCode OR u.village.parent.parent.parent.parent.name = :provinceName")
    List<com.supermarket.supermarket.domain.User> findUsersByProvinceCodeOrName(
        @Param("provinceCode") String provinceCode,
        @Param("provinceName") String provinceName
    );
}