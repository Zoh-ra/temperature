package com.temperature.temperature.projection;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class TemperatureProjection {
    private Double temperature;
    private String sonde;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dateArrondi;

    // Default constructor for JPA
    public TemperatureProjection() {
    }

    // Constructor with parameters
    public TemperatureProjection(Double temperature, String sonde, LocalDateTime dateArrondi) {
        this.temperature = temperature;
        this.sonde = sonde;
        this.dateArrondi = dateArrondi;
    }

    // Getters and Setters
    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public String getSonde() {
        return sonde;
    }

    public void setSonde(String sonde) {
        this.sonde = sonde;
    }

    public LocalDateTime getDateArrondi() {
        return dateArrondi;
    }

    public void setDateArrondi(LocalDateTime dateArrondi) {
        this.dateArrondi = dateArrondi;
    }
}