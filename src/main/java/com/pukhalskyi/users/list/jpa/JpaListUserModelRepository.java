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
    private final ModelMapper userModelmapper;

    public JpaListUserModelRepository(UserEntityRepository userEntityRepository, ModelMapper userModelmapper) {
        this.userEntityRepository = userEntityRepository;
        this.userModelmapper = userModelmapper;
    }

    public List<UserModel> findAll() {
        List<User> users = userEntityRepository.findAll();

        return users.stream()
                .map(user -> userModelmapper.map(user, UserModel.class))
                .collect(Collectors.toList());
    }

    public UserModel findByNickname(String nickname) {
        return userModelmapper.map(userEntityRepository.findByNickname(nickname), UserModel.class);
    }

    @Override
    public void addUser(UserModel userModel) {
        userEntityRepository.save(userModelmapper.map(userModel, User.class));
    }

    @Override
    public void deleteUser(Long id) {
        userEntityRepository.deleteById(id);
    }
}
