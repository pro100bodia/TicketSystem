package com.pukhalskyi.tickets;

import com.pukhalskyi.model.TicketModel;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface TicketRepository<T> {
    T findAll();

    T findByPlace(String title);

    T findByEvent(String title);

    T findByDateRange(LocalDateTime from, LocalDateTime to);

    void addTicket(TicketModel ticketModel);

    void deleteTicket(Long id);
}
