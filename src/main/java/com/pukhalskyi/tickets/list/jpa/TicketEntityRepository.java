package com.pukhalskyi.tickets.list.jpa;

import com.pukhalskyi.entity.Ticket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Repository
public interface TicketEntityRepository extends CrudRepository<Ticket, Long> {
    List<Ticket> findAll();

    @Query(value = "SELECT * FROM ticket \n" +
            "\t\tJOIN ticket_event te\n" +
            "\t\tON ticket.id = te.ticket_id\n" +
            "\t\tJOIN event e\n" +
            "\t\tON te.event_id=e.id\n" +
            "\t\tJOIN place\n" +
            "\t\tON e.place_id=place.id\n" +
            "\tWHERE place.title= :title",
            nativeQuery = true)
    Set<Ticket> findByEventsByPlacesTitle(@Param("title") String title);

    Set<Ticket> findByEventsTitle(String title);

    Set<Ticket> findByEventsOccurredAtBetween(LocalDateTime from, LocalDateTime to);
}
