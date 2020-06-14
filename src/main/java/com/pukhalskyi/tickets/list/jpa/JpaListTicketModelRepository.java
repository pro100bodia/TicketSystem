package com.pukhalskyi.tickets.list.jpa;

import com.pukhalskyi.entity.Ticket;
import com.pukhalskyi.model.TicketModel;
import com.pukhalskyi.tickets.TicketRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class JpaListTicketModelRepository implements TicketRepository {
    private final com.pukhalskyi.tickets.list.jpa.TicketEntityRepository ticketEntityRepository;
    private final ModelMapper ticketModelMapper;

    public JpaListTicketModelRepository(com.pukhalskyi.tickets.list.jpa.TicketEntityRepository ticketEntityRepository, ModelMapper ticketModelMapper) {
        this.ticketEntityRepository = ticketEntityRepository;
        this.ticketModelMapper = ticketModelMapper;
    }

    public List<TicketModel> findAll() {
        List<Ticket> tickets = ticketEntityRepository.findAll();

        return tickets.stream()
                .map(ticket -> ticketModelMapper.map(ticket, TicketModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void addTicket(TicketModel ticketModel) {
        ticketEntityRepository.save(ticketModelMapper.map(ticketModel, Ticket.class));
    }

    @Override
    public void deleteTicket(Long id) {
        ticketEntityRepository.deleteById(id);
    }
}
