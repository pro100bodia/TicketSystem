package com.pukhalskyi.users.list.jpa;

import com.pukhalskyi.entity.User;
import com.pukhalskyi.model.UserModel;
import com.pukhalskyi.users.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.util.List;

@Primary
@Repository
public class JpaListUserModelRepository implements UserRepository {
    private final UserEntityRepository userEntityRepository;
    private final ModelMapper modelMapper;

    public JpaListUserModelRepository(UserEntityRepository userEntityRepository, ModelMapper modelMapper) {
        this.userEntityRepository = userEntityRepository;
        this.modelMapper = modelMapper;
    }

    public List<UserModel> findAll() {
        List<User> users = userEntityRepository.findAll();

        // Define the target type
        Type targetListType = new TypeToken<List<UserModel>>() {
        }.getType();

        return modelMapper.map(users, targetListType);
    }
}
