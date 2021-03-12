package com.pukhalskyi.users;

import com.pukhalskyi.entity.Role;
import com.pukhalskyi.entity.User;
import com.pukhalskyi.model.TicketModel;
import com.pukhalskyi.model.UserModel;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

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
public class UserServiceTest {
    private static final LocalDateTime date1 = LocalDateTime.of(
            LocalDate.of(2019, 9, 11), LocalTime.of(12, 0, 0));
    private static final LocalDateTime date2 = LocalDateTime.of(
            LocalDate.of(2019, 9, 11), LocalTime.of(12, 30, 0));
    private static final LocalDateTime date3 = LocalDateTime.of(
            LocalDate.of(2019, 9, 11), LocalTime.of(13, 0, 0));
    private static final LocalDateTime date4 = LocalDateTime.of(
            LocalDate.of(2019, 9, 11), LocalTime.of(13, 30, 0));

    private static List<UserModel> userModels;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService subject;

    @BeforeClass
    public static void initUserModels() {
        TicketModel ticket1 = new TicketModel(1L, date1, 1, 1, 10.0, null, null);
        TicketModel ticket2 = new TicketModel(2L, date2, 2, 2, 20.0, null, null);
        TicketModel ticket3 = new TicketModel(3L, date3, 3, 3, 30.0, null, null);
        TicketModel ticket4 = new TicketModel(4L, date4, 4, 4, 40.0, null, null);

        Set<TicketModel> tickets1 = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(ticket1, ticket2)));
        Set<TicketModel> tickets2 = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(ticket3)));
        Set<TicketModel> tickets3 = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(ticket4)));

        UserModel user1 = new UserModel(1L, "serhiilytka", "1111", "Serhii", "Lytka",
                "serhii@gmail.com", Role.ROLE_ADMIN, tickets1);
        UserModel user2 = new UserModel(2L, "marypublic", "1111", "Mary", "Public",
                "'mary@gmail.com'", Role.ROLE_USER, tickets2);
        UserModel user3 = new UserModel(3L, "johndou", "1111", "John", "Dou",
                "'john@gmail.com'", Role.ROLE_USER, tickets3);
        userModels = Arrays.asList(user1, user2, user3);
    }

    @Test
    public void givenNoArgs_whenFindAll_thenReturnAllUsers() {
//        given

//        when
        when(userRepository.findAll()).thenReturn(userModels);
        Iterable<User> result = subject.findAll();

//        then
        assertThat(userModels).isEqualTo(result);
    }

    @Test
    public void givenNickname_whenFindByName_thenReturnUserModel() {
        //given
        String nickname = "johndou";

        //when
        when(userRepository.findByNickname("johndou")).thenReturn(userModels.get(2));
        UserModel result = subject.findByName(nickname);

        //then
        assertThat(userModels.get(2)).isEqualTo(result);
    }

    @Test
    public void givenUserModel_whenAddUser_thenVerifyUserIsAdded() {
        //given
        UserModel userModel = userModels.get(2);

        //when
        subject.addUser(userModel);

        //then
        verify(userRepository).addUser(userModel);
    }

    @Test
    public void givenUserId_whenDeleteUser_thenVerifyUserIsDeleted() {
        //given
        Long userId = 1L;

        //when
        subject.deleteUser(userId);

        //then
        verify(userRepository).deleteUser(userId);
    }
}