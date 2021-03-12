package com.pukhalskyi.tickets.pagination.jpa;

import com.pukhalskyi.entity.Role;
import com.pukhalskyi.entity.Ticket;
import com.pukhalskyi.entity.User;
import com.pukhalskyi.model.TicketModel;
import com.pukhalskyi.model.UserModel;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JpaPaginatedTicketModelRepositoryTest {
    private static final LocalDateTime date1 = LocalDateTime.of(
            LocalDate.of(2019, 9, 11), LocalTime.of(12, 0, 0));
    private static final LocalDateTime date2 = LocalDateTime.of(
            LocalDate.of(2019, 9, 11), LocalTime.of(12, 30, 0));
    private static final LocalDateTime date3 = LocalDateTime.of(
            LocalDate.of(2019, 9, 11), LocalTime.of(13, 0, 0));
    private static final LocalDateTime date4 = LocalDateTime.of(
            LocalDate.of(2019, 9, 11), LocalTime.of(13, 30, 0));
    private static Page<Ticket> tickets;
    private static Page<TicketModel> ticketModels;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private PaginatedTicketEntityRepository paginatedTicketEntityRepository;

    @InjectMocks
    private JpaPaginatedTicketModelRepository subject;

    @BeforeClass
    public static void initTickets() {
        User user1 = new User(1L, "serhiilytka", "1111", "Serhii", "Lytka",
                "serhii@gmail.com", Role.ROLE_ADMIN, null);
        User user2 = new User(2L, "marypublic", "1111", "Mary", "Public",
                "'mary@gmail.com'", Role.ROLE_USER, null);
        User user3 = new User(3L, "johndou", "1111", "John", "Dou",
                "'john@gmail.com'", Role.ROLE_USER, null);

        Set<User> users1 = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(user1)));
        Set<User> users2 = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(user1)));
        Set<User> users3 = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(user2, user3)));

        Ticket ticket1 = new Ticket(1L, date1, 1, 1, 10.0, users1, null);
        Ticket ticket2 = new Ticket(2L, date1, 2, 2, 20.0, users2, null);
        Ticket ticket3 = new Ticket(3L, date1, 3, 3, 30.0, users3, null);

        tickets = new PageImpl<>(Arrays.asList(ticket1, ticket2, ticket3));
    }

    @BeforeClass
    public static void initTicketModels() {
        UserModel user1 = new UserModel(1L, "serhiilytka", "1111", "Serhii", "Lytka",
                "serhii@gmail.com", Role.ROLE_ADMIN, null);
        UserModel user2 = new UserModel(2L, "marypublic", "1111", "Mary", "Public",
                "'mary@gmail.com'", Role.ROLE_USER, null);
        UserModel user3 = new UserModel(3L, "johndou", "1111", "John", "Dou",
                "'john@gmail.com'", Role.ROLE_USER, null);

        Set<UserModel> users1 = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(user1)));
        Set<UserModel> users2 = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(user1)));
        Set<UserModel> users3 = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(user2, user3)));

        TicketModel ticket1 = new TicketModel(1L, date1, 1, 1, 10.0, users1, null);
        TicketModel ticket2 = new TicketModel(2L, date1, 2, 2, 20.0, users2, null);
        TicketModel ticket3 = new TicketModel(3L, date1, 3, 3, 30.0, users3, null);

        ticketModels = new PageImpl<>(Arrays.asList(ticket1, ticket2, ticket3));
    }

    @Test
    public void givenNoArgs_whenFindAll_ThenReturnTicketModels() {
        //given

        //when
        when(paginatedTicketEntityRepository.findAll(PageRequest.of(0, 3))).thenReturn(tickets);

        java.lang.reflect.Type targetListType = new TypeToken<List<TicketModel>>() {
        }.getType();
        for (int i = 0; i < ticketModels.getContent().size(); i++) {
            when(modelMapper.map(tickets.getContent().get(i), TicketModel.class)).thenReturn(ticketModels.getContent().get(i));
        }

        Page<TicketModel> result = subject.findAll();
        //then
        assertThat(ticketModels).isEqualTo(result);
    }

    @Test
    public void givenTicketModel_whenAddTicket_thenVerifyTicketIsSaved() {
        //given
        TicketModel ticketModel = ticketModels.getContent().get(2);

        //when
        when(modelMapper.map(ticketModel, Ticket.class)).thenReturn(tickets.getContent().get(2));
        subject.addTicket(ticketModel);

        //then
        verify(paginatedTicketEntityRepository).save(tickets.getContent().get(2));
    }

    @Test
    public void givenTicketId_whenDeleteTicket_thenVerifyTicketIsDeleted() {
        //given
        Long ticketId = 1L;

        //when
        subject.deleteTicket(ticketId);

        //then
        verify(paginatedTicketEntityRepository).deleteById(ticketId);
    }
}