package com.pukhalskyi.dto;

import com.pukhalskyi.model.TicketModel;
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
public class EventDto {
    private Long id;
    private String title;
    private LocalDateTime createdAt;
    private LocalDateTime occurredAt;
    private PlaceDto place;
    private Set<TicketModel> tickets;
}
