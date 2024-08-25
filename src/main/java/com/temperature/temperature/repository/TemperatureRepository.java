package com.temperature.temperature.repository;

import com.temperature.temperature.entity.Temperature;
import com.temperature.temperature.projection.TemperatureProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TemperatureRepository extends JpaRepository<Temperature, Integer> {

    @Query("SELECT new com.temperature.temperature.projection.TemperatureProjection(s.temperature, s.sonde, s.dateArrondi) FROM Temperature s WHERE s.sonde = :sonde AND s.dateArrondi BETWEEN :startDateArrondi AND :endDateArrondi")
    List<TemperatureProjection> findTemperaturesBySondeAndDateBetween(
            @Param("sonde") String sonde,
            @Param("startDateArrondi") LocalDateTime startDateArrondi,
            @Param("endDateArrondi") LocalDateTime endDateArrondi);

    @Query("SELECT COUNT(s) FROM Temperature s WHERE s.sonde = :sonde AND s.dateArrondi BETWEEN :startDateArrondi AND :endDateArrondi")
    Integer countTemperaturesBySondeAndDatePeriod(@Param("sonde") String sonde, @Param("startDateArrondi") LocalDateTime startDateArrondi, @Param("endDateArrondi") LocalDateTime endDateArrondi);


    @Query("SELECT new com.temperature.temperature.projection.TemperatureProjection(s.temperature, s.sonde, s.dateArrondi) FROM Temperature s WHERE s.sonde = :sonde AND s.dateArrondi BETWEEN :startDateArrondi AND :endDateArrondi")
    List<TemperatureProjection> findMissingTemperaturesBySondeAndDateBetween(
            @Param("sonde") String sonde,
            @Param("startDateArrondi") LocalDateTime startDateArrondi,
            @Param("endDateArrondi") LocalDateTime endDateArrondi);
}