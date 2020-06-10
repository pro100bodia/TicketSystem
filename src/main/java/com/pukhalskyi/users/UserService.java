package com.pukhalskyi.users;

import com.pukhalskyi.model.UserModel;

public class UserService<T extends Iterable> {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    T findAll() {
        return (T) userRepository.findAll();
    }

    public UserModel findByName(String nickname) {
        return userRepository.findByNickname(nickname);
    }

    public void addUser(UserModel userModel) {
        userRepository.addUser(userModel);
    }

    public void deleteUser(Long id) {
        userRepository.deleteUser(id);
    }
}
