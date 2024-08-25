package com.temperature.temperature.service;

import com.temperature.temperature.dto.MissingTemperatureResponse;
import com.temperature.temperature.dto.TemperatureResponse;
import com.temperature.temperature.projection.TemperatureProjection;
import com.temperature.temperature.repository.TemperatureRepository;
import com.temperature.temperature.utils.DateTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TemperatureService {
    @Autowired
    private TemperatureRepository temperatureRepository;

    public TemperatureResponse getTemperaturesBySondeAndDatePeriod(String sonde, LocalDateTime startDate, LocalDateTime endDate) {
        List<TemperatureProjection> temperatures = temperatureRepository.findTemperaturesBySondeAndDateBetween(sonde, startDate, endDate);
        Integer totalTemperature = temperatureRepository.countTemperaturesBySondeAndDatePeriod(sonde, startDate, endDate);

        TemperatureResponse response = new TemperatureResponse();
        response.setDataTemperature(temperatures);
        response.setTotalTemperature(totalTemperature);

        return response;
    }

    public MissingTemperatureResponse getMissingTemperaturesBySondeAndDatePeriod(String sonde, LocalDateTime startDateArrondi, LocalDateTime endDateArrondi) {

        startDateArrondi = DateTimeUtils.roundToNearestTenMinutes(startDateArrondi);
        endDateArrondi = DateTimeUtils.roundToNearestTenMinutes(endDateArrondi);

        List<TemperatureProjection> recordedTemperatures = temperatureRepository.findMissingTemperaturesBySondeAndDateBetween(
                sonde, startDateArrondi, endDateArrondi);

        List<LocalDateTime> recordedDates = recordedTemperatures.stream()
                .map(TemperatureProjection::getDateArrondi)
                .collect(Collectors.toList());

        List<TemperatureProjection> missingIntervals = new ArrayList<>();

        LocalDateTime currentDateTime = startDateArrondi;
        while (currentDateTime.isBefore(endDateArrondi) || currentDateTime.isEqual(endDateArrondi)) {
            if (!recordedDates.contains(currentDateTime)) {
                missingIntervals.add(new TemperatureProjection(null, sonde, currentDateTime));
            }
            currentDateTime = currentDateTime.plusMinutes(10);
        }

        MissingTemperatureResponse response = new MissingTemperatureResponse();
        response.setDataMissingTemperature(missingIntervals);
        response.setTotalMissingTemperature(missingIntervals.size());

        return response;
    }
}
