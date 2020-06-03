package com.pukhalskyi.users.pagination.jpa;

import com.pukhalskyi.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaginatedUserEntityRepository extends PagingAndSortingRepository<User, Integer> {
    Page<User> findAll(Pageable pageable);
}
