package com.pukhalskyi.users;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository<T> {
    T findAll();
}
