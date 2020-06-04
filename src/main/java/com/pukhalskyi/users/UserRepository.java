package com.pukhalskyi.users;

import com.pukhalskyi.model.UserModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository {
    List<UserModel> findAll();
}
