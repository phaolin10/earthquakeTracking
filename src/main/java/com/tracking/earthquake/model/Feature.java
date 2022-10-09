package com.tracking.earthquake.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Feature {

    private String id;
    private String type;
    private Map<String, String> properties;

    @JsonIgnore
    private Geometry geometry;

}
