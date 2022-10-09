package com.tracking.earthquake.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tracking.earthquake.model.Earthquake;
import com.tracking.earthquake.model.Feature;
import com.tracking.earthquake.service.EarthquakeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class EarthquakeServiceImpl implements EarthquakeService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${earthquake_url}")
    private String earthquakeUrl;

    @Override
    public Object getEarthquakeData(LocalDate startDate, LocalDate endDate, final String country) {
        final String url = createUrl(startDate, endDate);

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        List<Earthquake> earthquakes = new ArrayList<>();
        try {
            JsonNode root = objectMapper.readTree(response.getBody());
            JsonNode features = root.path("features");

            List<Feature> featureList = Arrays.asList(objectMapper.readValue(features.toString(), Feature[].class));

            featureList.forEach(f -> {
                String place = f.getProperties().get("place");

                if (!Objects.isNull(place) && place.contains(country)) {
                    earthquakes.add(new Earthquake(country, f.getProperties().get("place"), Double.valueOf(f.getProperties().get("mag")), f.getProperties().get("time")));
                }
            });

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return earthquakes;
    }

    private String createUrl(LocalDate startDate, LocalDate endDate) {
        return earthquakeUrl.replace("START_DATE", startDate.toString())
                .replace("END_DATE", endDate.toString());
    }
}
