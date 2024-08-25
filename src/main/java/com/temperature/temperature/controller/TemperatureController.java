package com.temperature.temperature.controller;


import com.temperature.temperature.dto.MissingTemperatureResponse;
import com.temperature.temperature.dto.TemperatureResponse;
import com.temperature.temperature.service.TemperatureService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/temperature")
@Tag(name = "Sonde Controller", description = "Récupérer les températures d’une ou plusieurs sondes sur une période donnée")
public class TemperatureController {

    @Autowired
    private TemperatureService temperatureService;

    @Operation(summary = "Temperature par periode")
    @GetMapping("/sonde")
    public ResponseEntity<TemperatureResponse> getSondesData(

            @RequestParam String sonde,

            @Parameter(description = "Date de début au format 'yyyy-MM-ddTHH:mm:ss'")
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startDateArrondi,

            @Parameter(description = "Date de fin au format 'yyyy-MM-ddTHH:mm:ss'")
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endDateArrondi) {
        TemperatureResponse response = temperatureService.getTemperaturesBySondeAndDatePeriod(sonde, startDateArrondi, endDateArrondi);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Manquement de température par période")
    @GetMapping("/missing")
    public ResponseEntity<MissingTemperatureResponse> getMissingTemperatures(
            @RequestParam String sonde,

            @Parameter(description = "Date de début au format 'yyyy-MM-ddTHH:mm:ss'")
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startDateArrondi,

            @Parameter(description = "Date de fin au format 'yyyy-MM-ddTHH:mm:ss'")
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endDateArrondi) {
        MissingTemperatureResponse missingTemperatures = temperatureService.getMissingTemperaturesBySondeAndDatePeriod(sonde, startDateArrondi, endDateArrondi);
        return ResponseEntity.ok(missingTemperatures);
    }
}