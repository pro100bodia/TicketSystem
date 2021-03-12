package com.pukhalskyi.tickets.pagination.jpa;

import com.pukhalskyi.entity.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface PaginatedTicketEntityRepository extends PagingAndSortingRepository<Ticket, Integer> {
    Page<Ticket> findAll(Pageable pageable);

    @Query(value = "SELECT * FROM ticket \n" +
            "\t\tJOIN ticket_event te\n" +
            "\t\tON ticket.id = te.ticket_id\n" +
            "\t\tJOIN event e\n" +
            "\t\tON te.event_id=e.id\n" +
            "\t\tJOIN place\n" +
            "\t\tON e.place_id=place.id\n" +
            "\tWHERE place.title= :title",
            nativeQuery = true)
    Page<Ticket> findByPlacesTitle(@Param("title") String title, Pageable pageable);

    Page<Ticket> findByEventsTitle(String title, Pageable pageable);

    Page<Ticket> findByEventsOccurredAtBetween(LocalDateTime from, LocalDateTime to, Pageable pageable);

    Ticket save(Ticket ticket);

    void deleteById(Long integer);
}
