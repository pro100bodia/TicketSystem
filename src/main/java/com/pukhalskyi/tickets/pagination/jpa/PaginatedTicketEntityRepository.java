package com.pukhalskyi.tickets.pagination.jpa;

import com.pukhalskyi.entity.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaginatedTicketEntityRepository extends PagingAndSortingRepository<Ticket, Integer> {
    Page<Ticket> findAll(Pageable pageable);

    Ticket save(Ticket ticket);

    void deleteById(Long integer);
}
