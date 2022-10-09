package com.tracking.earthquake.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Earthquake {

    private String country;
    private String place;
    private Double magnitude;
    private String timeStamp;
}
