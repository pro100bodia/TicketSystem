package com.pukhalskyi.users.list.jpa;

import com.pukhalskyi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserEntityRepository extends JpaRepository<User, Long> {
    List<User> findAll();

    User findByNickname(String nickname);
}
