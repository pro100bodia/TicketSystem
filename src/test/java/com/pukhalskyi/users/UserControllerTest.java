package com.pukhalskyi.users;

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
public class UserControllerTest {

    private static final LocalDateTime date1 = LocalDateTime.of(
            LocalDate.of(2019, 9, 11), LocalTime.of(12, 0, 0));
    private static final LocalDateTime date2 = LocalDateTime.of(
            LocalDate.of(2019, 9, 11), LocalTime.of(12, 30, 0));
    private static final LocalDateTime date3 = LocalDateTime.of(
            LocalDate.of(2019, 9, 11), LocalTime.of(13, 0, 0));
    private static final LocalDateTime date4 = LocalDateTime.of(
            LocalDate.of(2019, 9, 11), LocalTime.of(13, 30, 0));
    private static List<UserModel> userModels;
    private static List<UserDto> userDtos;
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    @Mock
    private UserService userService;
    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UserController subject;

    @BeforeClass
    public static void initUserModels() {
        TicketModel ticket1 = new TicketModel(1L, date1, 1, 1, 10.0, null, null);
        TicketModel ticket2 = new TicketModel(2L, date1, 2, 2, 20.0, null, null);
        TicketModel ticket3 = new TicketModel(3L, date1, 3, 3, 30.0, null, null);
        TicketModel ticket4 = new TicketModel(4L, date1, 4, 4, 40.0, null, null);


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

    @BeforeClass
    public static void initUserDtos() {
        TicketDto ticket1 = new TicketDto(1L, date1, 1, 1, 10.0, null, null);
        TicketDto ticket2 = new TicketDto(2L, date2, 2, 2, 20.0, null, null);
        TicketDto ticket3 = new TicketDto(3L, date3, 3, 3, 30.0, null, null);
        TicketDto ticket4 = new TicketDto(4L, date4, 4, 4, 40.0, null, null);


        Set<TicketDto> tickets1 = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(ticket1, ticket2)));
        Set<TicketDto> tickets2 = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(ticket3)));
        Set<TicketDto> tickets3 = Collections.unmodifiableSet(new HashSet<>(Arrays.asList(ticket4)));

        UserDto user1 = new UserDto(1L, "serhiilytka", "1111", "Serhii", "Lytka",
                "serhii@gmail.com", Role.ROLE_ADMIN, tickets1);
        UserDto user2 = new UserDto(2L, "marypublic", "1111", "Mary", "Public",
                "'mary@gmail.com'", Role.ROLE_USER, tickets2);
        UserDto user3 = new UserDto(3L, "johndou", "1111", "John", "Dou",
                "'john@gmail.com'", Role.ROLE_USER, tickets3);
        userDtos = Arrays.asList(user1, user2, user3);
    }

    @Test
    public void givenNoArgs_whenFindAll_thenReturnUsersArray() {
        //given

        //when
        when(userService.findAll()).thenReturn(userModels);

        java.lang.reflect.Type targetListType = new TypeToken<Iterable<UserDto>>() {
        }.getType();
        when(modelMapper.map(userModels, targetListType)).thenReturn(userDtos);

        ResponseEntity<?> result = subject.findAll();

        //then
        assertThat(userDtos).isEqualTo(result.getBody());
    }

    @Test
    public void givenUserNickname_whenFindByName_thenReturnUserDto() {
        //given
        String nickname = "johndou";

        //when
        when(userService.findByName(nickname)).thenReturn(userModels.get(2));
        when(modelMapper.map(userModels.get(2), UserDto.class)).thenReturn(userDtos.get(2));

        ResponseEntity<UserDto> result = subject.findByName(nickname);

        //then
        assertThat(userDtos.get(2)).isEqualTo(result.getBody());
    }

    @Test
    public void givenUserDto_whenAddUser_thenVerifyUserIsAdded() {
        //given
        UserDto userDto = userDtos.get(2);

        //when
        when(modelMapper.map(userDto, UserModel.class)).thenReturn(userModels.get(2));
        subject.addUser(userDto);

        //then
        verify(userService).addUser(userModels.get(2));
    }

    @Test
    public void givenUserId_whenDeleteUser_thenVerifyUserIsDeleted() {
        //given
        Long userId = 1L;

        //when
        subject.deleteUser(userId);

        //then
        verify(userService).deleteUser(userId);
    }
}