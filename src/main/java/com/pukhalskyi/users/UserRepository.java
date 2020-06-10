package com.pukhalskyi.users;

import com.pukhalskyi.model.UserModel;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository<T> {
    T findAll();

    UserModel findByNickname(String nickname);

    void addUser(UserModel userModel);

    void deleteUser(Long id);
}
