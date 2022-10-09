package com.tracking.earthquake.service;

import java.time.LocalDate;

public interface EarthquakeService {
    Object getEarthquakeData(LocalDate startDate, LocalDate endDate, String country);
}
