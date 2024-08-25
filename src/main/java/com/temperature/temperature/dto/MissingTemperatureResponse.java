package com.temperature.temperature.dto;

import com.temperature.temperature.projection.TemperatureProjection;

import java.util.List;

public class MissingTemperatureResponse {
    private List<TemperatureProjection> dataMissingTemperature;
    private Integer totalMissingTemperature;

    // Getters and Setters

    public List<TemperatureProjection> getDataMissingTemperature() {
        return dataMissingTemperature;
    }

    public void setDataMissingTemperature(List<TemperatureProjection> dataMissingTemperature) {
        this.dataMissingTemperature = dataMissingTemperature;
    }

    public Integer getTotalMissingTemperature() {
        return totalMissingTemperature;
    }

    public void setTotalMissingTemperature(Integer totalMissingTemperature) {
        this.totalMissingTemperature = totalMissingTemperature;
    }
}
