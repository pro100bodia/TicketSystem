package com.pukhalskyi.tickets;

import com.pukhalskyi.dto.TicketDto;
import com.pukhalskyi.model.TicketModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;

@RestController
@RequestMapping("/*/api/tickets")
public class TicketController {
    private final TicketService ticketService;
    private final ModelMapper ticketModelMapper;

    public TicketController(TicketService ticketService, ModelMapper ticketModelMapper) {
        this.ticketService = ticketService;
        this.ticketModelMapper = ticketModelMapper;
    }

    @GetMapping()
    public ResponseEntity<?> findAll() {
        Iterable<TicketModel> ticketModels = ticketService.findAll();
        Type targetIterableType = new TypeToken<Iterable<TicketDto>>() {
        }.getType();
        Iterable<TicketDto> ticketDtos = ticketModelMapper.map(ticketModels, targetIterableType);

        return new ResponseEntity<>(ticketDtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addTicket(@RequestBody TicketDto ticketDto) {
        ticketService.addTicket(ticketModelMapper.map(ticketDto, TicketModel.class));

        return new ResponseEntity<>("Ticket registration complete!", HttpStatus.OK);
    }

    @DeleteMapping("/{ticketId}")
    public ResponseEntity<String> deleteTicket(@PathVariable Long ticketId) {
        ticketService.deleteTicket(ticketId);

        return new ResponseEntity<>("User deleted!", HttpStatus.OK);
    }
}
