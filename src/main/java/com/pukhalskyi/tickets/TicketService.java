package com.pukhalskyi.tickets;

import com.pukhalskyi.entity.Ticket;
import com.pukhalskyi.model.TicketModel;

import java.time.LocalDateTime;

public class TicketService<T extends Iterable> {
    private final TicketRepository<Ticket> ticketRepository;

    public TicketService(TicketRepository<Ticket> ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    T findAll() {
        return (T) ticketRepository.findAll();
    }

    T findByPlace(String title) {
        return (T) ticketRepository.findByPlace(title);
    }

    T findByEvent(String title) {
        return (T) ticketRepository.findByEvent(title);
    }

    T findByDateRange(LocalDateTime from, LocalDateTime to) {
        return (T) ticketRepository.findByDateRange(from, to);
    }

    public void addTicket(TicketModel ticketModel) {
        ticketRepository.addTicket(ticketModel);
    }

    public void deleteTicket(Long id) {
        ticketRepository.deleteTicket(id);
    }
}
