package com.pukhalskyi.dto;

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
public class TicketDto {
    private Long id;
    private LocalDateTime boughtAt;
    private int tier;
    private int seat;
    private double price;

    private Set<UserDto> users;
    private Set<EventDto> events;
}
