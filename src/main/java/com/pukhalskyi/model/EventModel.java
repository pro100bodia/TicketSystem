package com.pukhalskyi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EventModel {
    private Long id;
    private String title;
    private LocalDateTime createdAt;
    private LocalDateTime occurredAt;
    private PlaceModel place;
    private Set<TicketModel> tickets;
}
