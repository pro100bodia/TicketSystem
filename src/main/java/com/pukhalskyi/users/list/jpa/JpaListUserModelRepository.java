package com.pukhalskyi.users.list.jpa;

import com.pukhalskyi.entity.User;
import com.pukhalskyi.model.UserModel;
import com.pukhalskyi.users.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class JpaListUserModelRepository implements UserRepository {
    private final UserEntityRepository userEntityRepository;
    private final ModelMapper ueserModelMapper;

    public JpaListUserModelRepository(UserEntityRepository userEntityRepository, ModelMapper ueserModelMapper) {
        this.userEntityRepository = userEntityRepository;
        this.ueserModelMapper = ueserModelMapper;
    }

    public List<UserModel> findAll() {
        List<User> users = userEntityRepository.findAll();

        return users.stream()
                .map(user -> ueserModelMapper.map(user, UserModel.class))
                .collect(Collectors.toList());
    }
}
