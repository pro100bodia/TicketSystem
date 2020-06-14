package com.pukhalskyi.tickets.list.jpa;

import com.pukhalskyi.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketEntityRepository extends JpaRepository<Ticket, Long> {
    List<Ticket> findAll();


}
