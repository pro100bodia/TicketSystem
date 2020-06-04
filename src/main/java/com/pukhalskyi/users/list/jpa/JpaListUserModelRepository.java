package com.pukhalskyi.users.list.jpa;

import com.pukhalskyi.entity.Ticket;
import com.pukhalskyi.entity.User;
import com.pukhalskyi.model.TicketModel;
import com.pukhalskyi.model.UserModel;
import com.pukhalskyi.users.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Primary
@Repository
public class JpaListUserModelRepository implements UserRepository {
    private final UserEntityRepository userEntityRepository;
    private final ModelMapper modelMapper;

    public JpaListUserModelRepository(UserEntityRepository userEntityRepository, ModelMapper modelMapper) {
        this.userEntityRepository = userEntityRepository;
        this.modelMapper = modelMapper;
    }

    public List<UserModel> findAll() {
        List<User> users = userEntityRepository.findAll();

        return users.stream()
                .map(this::mapTicketsSet)
                .collect(Collectors.toList());
    }

    private UserModel mapTicketsSet(User user) {
        TypeMap<Ticket, TicketModel> typeMap = modelMapper.getTypeMap(Ticket.class, TicketModel.class);

        if (typeMap == null) {
            modelMapper.addMappings(new PropertyMap<Ticket, TicketModel>() {
                @Override
                protected void configure() {
                    skip(destination.getUsers());
                }
            });
        }

        Set<TicketModel> ticketModels = user.getTickets().stream()
                .map(p -> modelMapper.map(p, TicketModel.class))
                .collect(Collectors.toSet());

        UserModel userModel = modelMapper.map(user, UserModel.class);
        userModel.setTickets(ticketModels);

        return userModel;
    }
}
