package com.pukhalskyi.config;

import com.pukhalskyi.entity.Ticket;
import com.pukhalskyi.model.TicketModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public ModelMapper userModelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        TypeMap<Ticket, TicketModel> typeMap = modelMapper.getTypeMap(Ticket.class, TicketModel.class);

        if (typeMap == null) {
            modelMapper.addMappings(new PropertyMap<Ticket, TicketModel>() {
                @Override
                protected void configure() {
                    skip(destination.getUsers());
                }
            });
        }

        return modelMapper;
    }
}
