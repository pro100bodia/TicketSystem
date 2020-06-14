package com.pukhalskyi.tickets;

import com.pukhalskyi.entity.Ticket;
import com.pukhalskyi.model.TicketModel;

public class TicketService<T extends Iterable> {
    private final TicketRepository<Ticket> ticketRepository;

    public TicketService(TicketRepository<Ticket> ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    T findAll() {
        return (T) ticketRepository.findAll();
    }

    public void addTicket(TicketModel ticketModel) {
        ticketRepository.addTicket(ticketModel);
    }

    public void deleteTicket(Long id) {
        ticketRepository.deleteTicket(id);
    }
}
