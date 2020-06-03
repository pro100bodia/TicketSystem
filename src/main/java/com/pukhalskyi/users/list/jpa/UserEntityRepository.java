package com.pukhalskyi.users.list.jpa;

import com.pukhalskyi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Primary
@Repository
public interface UserEntityRepository extends JpaRepository<User, Long> {
//    User findByUsername(String username);
//
//    User save(User user);
}
