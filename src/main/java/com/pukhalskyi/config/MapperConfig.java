package com.pukhalskyi.config;

import com.pukhalskyi.entity.Event;
import com.pukhalskyi.entity.Place;
import com.pukhalskyi.entity.Ticket;
import com.pukhalskyi.entity.User;
import com.pukhalskyi.model.EventModel;
import com.pukhalskyi.model.PlaceModel;
import com.pukhalskyi.model.TicketModel;
import com.pukhalskyi.model.UserModel;
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

        TypeMap<Place, PlaceModel> placeModelTypeMap = modelMapper.getTypeMap(Place.class, PlaceModel.class);
        if (placeModelTypeMap == null) {
            modelMapper.addMappings(new PropertyMap<Place, PlaceModel>() {
                @Override
                protected void configure() {
                    skip(destination.getEvents());
                }
            });
        }

        TypeMap<Event, EventModel> eventModelTypeMap = modelMapper.getTypeMap(Event.class, EventModel.class);
        if (eventModelTypeMap == null) {
            modelMapper.addMappings(new PropertyMap<Event, EventModel>() {
                @Override
                protected void configure() {
                    skip(destination.getTickets());
                }
            });
        }

        TypeMap<Ticket, TicketModel> ticketModelTypeMap = modelMapper.getTypeMap(Ticket.class, TicketModel.class);
        if (ticketModelTypeMap == null) {
            modelMapper.addMappings(new PropertyMap<Ticket, TicketModel>() {
                @Override
                protected void configure() {
                    skip(destination.getUsers());
                }
            });
        }

        return modelMapper;
    }

    @Bean
    public ModelMapper ticketModelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        TypeMap<Place, PlaceModel> placeModelTypeMap = modelMapper.getTypeMap(Place.class, PlaceModel.class);
        if (placeModelTypeMap == null) {
            modelMapper.addMappings(new PropertyMap<Place, PlaceModel>() {
                @Override
                protected void configure() {
                    skip(destination.getEvents());
                }
            });
        }

        TypeMap<Event, EventModel> eventModelTypeMap = modelMapper.getTypeMap(Event.class, EventModel.class);
        if (eventModelTypeMap == null) {
            modelMapper.addMappings(new PropertyMap<Event, EventModel>() {
                @Override
                protected void configure() {
                    skip(destination.getTickets());
                }
            });
        }

        TypeMap<User, UserModel> ticketModelTypeMap = modelMapper.getTypeMap(User.class, UserModel.class);
        if (ticketModelTypeMap == null) {
            modelMapper.addMappings(new PropertyMap<User, UserModel>() {
                @Override
                protected void configure() {
                    skip(destination.getTickets());
                }
            });
        }

        return modelMapper;
    }
}
