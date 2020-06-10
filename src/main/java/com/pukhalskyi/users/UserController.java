package com.pukhalskyi.users;

import com.pukhalskyi.dto.UserDto;
import com.pukhalskyi.model.UserModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Type;

@RestController
@RequestMapping("/*/api/users")
public class UserController {
    private final UserService userService;
    private final ModelMapper userModelMapper;

    public UserController(UserService userService, ModelMapper userModelMapper) {
        this.userService = userService;
        this.userModelMapper = userModelMapper;
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        Iterable<UserModel> userModels = userService.findAll();
        Type targetIterableType = new TypeToken<Iterable<UserDto>>() {
        }.getType();
        Iterable<UserDto> userDtos = userModelMapper.map(userModels, targetIterableType);

        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<UserDto> findByName(@RequestParam String nickname) {
        return new ResponseEntity<>(userModelMapper.map(userService.findByName(nickname), UserDto.class), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addUser(@RequestBody UserDto userDto) {
        userService.addUser(userModelMapper.map(userDto, UserModel.class));

        return new ResponseEntity<>("User registration complete!", HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUser(@RequestParam Long userId) {
        userService.deleteUser(userId);

        return new ResponseEntity<>("User deleted!", HttpStatus.OK);
    }
}
