package com.temperature.temperature.dto;

import com.temperature.temperature.projection.TemperatureProjection;

import java.util.List;

public class TemperatureResponse {
    private List<TemperatureProjection> dataTemperature;
    private Integer totalTemperature;

    // Getters and Setters

    public List<TemperatureProjection> getDataTemperature() {
        return dataTemperature;
    }

    public void setDataTemperature(List<TemperatureProjection> dataTemperature) {
        this.dataTemperature = dataTemperature;
    }

    public Integer getTotalTemperature() {
        return totalTemperature;
    }

    public void setTotalTemperature(Integer totalTemperature) {
        this.totalTemperature = totalTemperature;
    }
}