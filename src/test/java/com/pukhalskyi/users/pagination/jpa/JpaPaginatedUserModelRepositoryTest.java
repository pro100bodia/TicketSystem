package com.pukhalskyi.users.pagination.jpa;

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
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JpaPaginatedUserModelRepositoryTest {
    private static final LocalDateTime date1 = LocalDateTime.of(
            LocalDate.of(2019, 9, 11), LocalTime.of(12, 0, 0));
    private static final LocalDateTime date2 = LocalDateTime.of(
            LocalDate.of(2019, 9, 11), LocalTime.of(12, 30, 0));
    private static final LocalDateTime date3 = LocalDateTime.of(
            LocalDate.of(2019, 9, 11), LocalTime.of(13, 0, 0));
    private static final LocalDateTime date4 = LocalDateTime.of(
            LocalDate.of(2019, 9, 11), LocalTime.of(13, 30, 0));
    private static Page<User> users;
    private static Page<UserModel> userModels;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private PaginatedUserEntityRepository paginatedUserEntityRepository;

    @InjectMocks
    private JpaPaginatedUserModelRepository subject;

    @BeforeClass
    public static void initUsers() {
        Ticket ticket1 = new Ticket(1L, date1, 1, 1, 10.0, null);
        Ticket ticket2 = new Ticket(2L, date1, 2, 2, 20.0, null);
        Ticket ticket3 = new Ticket(3L, date1, 3, 3, 30.0, null);
        Ticket ticket4 = new Ticket(4L, date1, 4, 4, 40.0, null);

        Set<Ticket> tickets1 = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(ticket1, ticket2)));
        Set<Ticket> tickets2 = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(ticket3)));
        Set<Ticket> tickets3 = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(ticket4)));

        User user1 = new User(1L, "serhiilytka", "1111", "Serhii", "Lytka",
                "serhii@gmail.com", Role.ROLE_ADMIN, tickets1);
        User user2 = new User(2L, "marypublic", "1111", "Mary", "Public",
                "'mary@gmail.com'", Role.ROLE_USER, tickets2);
        User user3 = new User(3L, "johndou", "1111", "John", "Dou",
                "'john@gmail.com'", Role.ROLE_USER, tickets3);
        users = new PageImpl<>(Arrays.asList(user1, user2, user3));
    }

    @BeforeClass
    public static void initUserModels() {
        TicketModel ticket1 = new TicketModel(1L, date1, 1, 1, 10.0, null);
        TicketModel ticket2 = new TicketModel(2L, date1, 2, 2, 20.0, null);
        TicketModel ticket3 = new TicketModel(3L, date1, 3, 3, 30.0, null);
        TicketModel ticket4 = new TicketModel(4L, date1, 4, 4, 40.0, null);

        Set<TicketModel> tickets1 = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(ticket1, ticket2)));
        Set<TicketModel> tickets2 = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(ticket3)));
        Set<TicketModel> tickets3 = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(ticket4)));

        UserModel user1 = new UserModel(1L, "serhiilytka", "1111", "Serhii", "Lytka",
                "serhii@gmail.com", Role.ROLE_ADMIN, tickets1);
        UserModel user2 = new UserModel(2L, "marypublic", "1111", "Mary", "Public",
                "'mary@gmail.com'", Role.ROLE_USER, tickets2);
        UserModel user3 = new UserModel(3L, "johndou", "1111", "John", "Dou",
                "'john@gmail.com'", Role.ROLE_USER, tickets3);
        userModels = new PageImpl<>(Arrays.asList(user1, user2, user3));
    }

    @Test
    public void givenNoArgs_whenFindAll_ThenReturnUserModels() {
        //given

        //when
        when(paginatedUserEntityRepository.findAll(PageRequest.of(0, 3))).thenReturn(users);

        java.lang.reflect.Type targetListType = new TypeToken<List<UserModel>>() {
        }.getType();
        for (int i = 0; i < userModels.getContent().size(); i++) {
            when(modelMapper.map(users.getContent().get(i), UserModel.class)).thenReturn(userModels.getContent().get(i));
        }

        Page<UserModel> result = subject.findAll();
        //then
        assertThat(userModels).isEqualTo(result);
    }


}