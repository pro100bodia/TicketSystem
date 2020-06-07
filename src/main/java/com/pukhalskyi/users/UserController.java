package com.pukhalskyi.users;

import com.pukhalskyi.dto.UserDto;
import com.pukhalskyi.model.UserModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
