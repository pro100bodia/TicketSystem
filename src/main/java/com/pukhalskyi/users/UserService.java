package com.pukhalskyi.users;

import org.springframework.stereotype.Service;

@Service
public class UserService<T extends Iterable> {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    T findAll() {
        return (T) userRepository.findAll();
    }
}
