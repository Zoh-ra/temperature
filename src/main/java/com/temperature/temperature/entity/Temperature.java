package com.temperature.temperature.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "temperaturesondeard")
public class Temperature {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String sonde;
    private LocalDateTime date;
    private LocalDateTime dateArrondi;
    private Double temperature;
    private Double tensionBatterie;
    private Double rssi;
    private String concentrateur;
    private LocalDateTime heureReception;
    private Boolean archivage;
    private Integer intervalleMesure;
}