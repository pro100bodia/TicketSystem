package com.pukhalskyi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PlaceModel {
    private Long id;
    private String title;
    private String country;
    private String city;
    private String state;
    private String street;
    private String building;
    private String placesNum;

    private Set<EventModel> events;
}
