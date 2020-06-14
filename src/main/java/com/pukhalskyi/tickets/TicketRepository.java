package com.pukhalskyi.tickets;

import com.pukhalskyi.model.TicketModel;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository<T> {
    T findAll();

    void addTicket(TicketModel ticketModel);

    void deleteTicket(Long id);
}
