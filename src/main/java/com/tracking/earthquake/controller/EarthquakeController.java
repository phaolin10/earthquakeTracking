package com.tracking.earthquake.controller;

import com.tracking.earthquake.service.EarthquakeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/earthquake")
@RequiredArgsConstructor
@Slf4j
public class EarthquakeController {

    private final EarthquakeService earthquakeService;

    @GetMapping(value = "{country}/{countOfDays}")
    public ResponseEntity<Object> getEarthquakeByTime(@PathVariable String country, @PathVariable int countOfDays) {
        return ResponseEntity.ok(earthquakeService.getEarthquakeData(LocalDate.now().minusDays(countOfDays), LocalDate.now(), country));

    }

}
