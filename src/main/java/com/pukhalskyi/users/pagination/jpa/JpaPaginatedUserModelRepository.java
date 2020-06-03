package com.pukhalskyi.users.pagination.jpa;

import com.pukhalskyi.entity.User;
import com.pukhalskyi.model.UserModel;
import com.pukhalskyi.users.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.util.List;

@Repository
public class JpaPaginatedUserModelRepository implements UserRepository {
    private final PaginatedUserEntityRepository paginatedUserEntityRepository;
    private final ModelMapper modelMapper;
    private int page, size;

    @Autowired
    public JpaPaginatedUserModelRepository(PaginatedUserEntityRepository paginatedUserEntityRepository,
                                           ModelMapper modelMapper) {
        this.paginatedUserEntityRepository = paginatedUserEntityRepository;
        this.modelMapper = modelMapper;
        this.page = 0;
        this.size = 3;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public Page<UserModel> findAll() {
        Pageable pageable = PageRequest.of(page, size);

        Page<User> users = paginatedUserEntityRepository.findAll(pageable);

        List<User> usersList = users.getContent();

        Type targetPageType = new TypeToken<List<UserModel>>() {
        }.getType();

        List<UserModel> userModelsList = modelMapper.map(usersList, targetPageType);

        return new PageImpl<>(userModelsList);
    }
}
