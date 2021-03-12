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
public class TicketModel {
    private Long id;
    private LocalDateTime boughtAt;
    private int tier;
    private int seat;
    private double price;

    private Set<UserModel> users;
    private Set<EventModel> events;
}
