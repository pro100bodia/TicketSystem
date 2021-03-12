package com.pukhalskyi.tickets.pagination.jpa;

import com.pukhalskyi.entity.Ticket;
import com.pukhalskyi.model.TicketModel;
import com.pukhalskyi.tickets.TicketRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Repository
public class JpaPaginatedTicketModelRepository implements TicketRepository {
    private final PaginatedTicketEntityRepository paginatedTicketEntityRepository;
    private final ModelMapper ticketModelMapper;
    private int page, size;

    @Autowired
    public JpaPaginatedTicketModelRepository(PaginatedTicketEntityRepository paginatedTicketEntityRepository,
                                             ModelMapper ticketModelMapper) {
        this.paginatedTicketEntityRepository = paginatedTicketEntityRepository;
        this.ticketModelMapper = ticketModelMapper;
        this.page = 0;
        this.size = 3;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public Page<TicketModel> findAll() {
        Pageable pageable = PageRequest.of(page, size);

        Page<Ticket> tickets = paginatedTicketEntityRepository.findAll(pageable);

        return new PageImpl<>(tickets.stream()
                .map(ticket -> ticketModelMapper.map(ticket, TicketModel.class))
                .collect(Collectors.toList()));
    }

    @Override
    public Page<TicketModel> findByPlace(String title) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Ticket> tickets = paginatedTicketEntityRepository.findByPlacesTitle(title, pageable);

        return new PageImpl<>(tickets.stream()
                .map(ticket -> ticketModelMapper.map(ticket, TicketModel.class))
                .collect(Collectors.toList()));
    }

    @Override
    public Page<TicketModel> findByEvent(String title) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Ticket> tickets = paginatedTicketEntityRepository.findByEventsTitle(title, pageable);

        return new PageImpl<>(tickets.stream()
                .map(ticket -> ticketModelMapper.map(ticket, TicketModel.class))
                .collect(Collectors.toList()));
    }

    @Override
    public Page<TicketModel> findByDateRange(LocalDateTime from, LocalDateTime to) {
        Pageable pageable = PageRequest.of(page, size);

        Page<Ticket> tickets = paginatedTicketEntityRepository.findByEventsOccurredAtBetween(from, to, pageable);

        return new PageImpl<>(tickets.stream()
                .map(ticket -> ticketModelMapper.map(ticket, TicketModel.class))
                .collect(Collectors.toList()));
    }

    @Override
    public void addTicket(TicketModel ticketModel) {
        paginatedTicketEntityRepository.save(ticketModelMapper.map(ticketModel, Ticket.class));
    }

    @Override
    public void deleteTicket(Long id) {
        paginatedTicketEntityRepository.deleteById(id);
    }
}
