package com.pukhalskyi.tickets;

import com.pukhalskyi.dto.TicketDto;
import com.pukhalskyi.dto.UserDto;
import com.pukhalskyi.entity.Role;
import com.pukhalskyi.model.TicketModel;
import com.pukhalskyi.model.UserModel;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.ResponseEntity;

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
public class TicketControllerTest {

    private static final LocalDateTime date1 = LocalDateTime.of(
            LocalDate.of(2019, 9, 11), LocalTime.of(12, 0, 0));
    private static final LocalDateTime date2 = LocalDateTime.of(
            LocalDate.of(2019, 9, 11), LocalTime.of(12, 30, 0));
    private static final LocalDateTime date3 = LocalDateTime.of(
            LocalDate.of(2019, 9, 11), LocalTime.of(13, 0, 0));
    private static final LocalDateTime date4 = LocalDateTime.of(
            LocalDate.of(2019, 9, 11), LocalTime.of(13, 30, 0));
    private static List<TicketModel> ticketModels;
    private static List<TicketDto> ticketDtos;
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    @Mock
    private TicketService ticketService;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private TicketController subject;

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

        ticketModels = Arrays.asList(ticket1, ticket2, ticket3);
    }

    @BeforeClass
    public static void initTicketDtos() {
        UserDto user1 = new UserDto(1L, "serhiilytka", "1111", "Serhii", "Lytka",
                "serhii@gmail.com", Role.ROLE_ADMIN, null);
        UserDto user2 = new UserDto(2L, "marypublic", "1111", "Mary", "Public",
                "'mary@gmail.com'", Role.ROLE_USER, null);
        UserDto user3 = new UserDto(3L, "johndou", "1111", "John", "Dou",
                "'john@gmail.com'", Role.ROLE_USER, null);

        Set<UserDto> users1 = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(user1)));
        Set<UserDto> users2 = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(user1)));
        Set<UserDto> users3 = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(user2, user3)));

        TicketDto ticket1 = new TicketDto(1L, date1, 1, 1, 10.0, users1, null);
        TicketDto ticket2 = new TicketDto(2L, date1, 2, 2, 20.0, users2, null);
        TicketDto ticket3 = new TicketDto(3L, date1, 3, 3, 30.0, users3, null);

        ticketDtos = Arrays.asList(ticket1, ticket2, ticket3);
    }

    @Test
    public void givenNoArgs_whenFindAll_thenReturnTicketsArray() {
        //given

        //when
        when(ticketService.findAll()).thenReturn(ticketModels);

        java.lang.reflect.Type targetListType = new TypeToken<Iterable<TicketDto>>() {
        }.getType();
        when(modelMapper.map(ticketModels, targetListType)).thenReturn(ticketDtos);

        ResponseEntity<?> result = subject.findAll();

        //then
        assertThat(ticketDtos).isEqualTo(result.getBody());
    }

    @Test
    public void givenTicketDto_whenAddTicket_thenVerifyTicketIsAdded() {
        //given
        TicketDto ticketDto = ticketDtos.get(2);

        //when
        when(modelMapper.map(ticketDto, TicketModel.class)).thenReturn(ticketModels.get(2));
        subject.addTicket(ticketDto);

        //then
        verify(ticketService).addTicket(ticketModels.get(2));
    }

    @Test
    public void givenTicketId_whenDeleteTicket_thenVerifyTicketIsDeleted() {
        //given
        Long ticketId = 1L;

        //when
        subject.deleteTicket(ticketId);

        //then
        verify(ticketService).deleteTicket(ticketId);
    }
}