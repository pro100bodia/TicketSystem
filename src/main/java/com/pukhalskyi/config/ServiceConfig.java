package com.pukhalskyi.config;

import com.pukhalskyi.entity.User;
import com.pukhalskyi.tickets.TicketService;
import com.pukhalskyi.tickets.list.jpa.JpaListTicketModelRepository;
import com.pukhalskyi.tickets.pagination.jpa.JpaPaginatedTicketModelRepository;
import com.pukhalskyi.users.UserService;
import com.pukhalskyi.users.list.jpa.JpaListUserModelRepository;
import com.pukhalskyi.users.pagination.jpa.JpaPaginatedUserModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.Page;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Configuration
public class ServiceConfig {
    @Autowired
    private JpaPaginatedUserModelRepository jpaPaginatedUserModelRepository;
    @Autowired
    private JpaListUserModelRepository jpaListUserModelRepository;
    @Autowired
    private JpaPaginatedTicketModelRepository jpaPaginatedTicketModelRepository;
    @Autowired
    private JpaListTicketModelRepository jpaListTicketModelRepository;

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public UserService<?> userService(HttpServletRequest request) {
        String url = request.getRequestURI();
        if (url.contains("v2")) {
            int page = Integer.parseInt(Optional.ofNullable(request.getParameter("page")).orElse("0"));
            int size = Integer.parseInt(Optional.ofNullable(request.getParameter("size")).orElse("3"));

            jpaPaginatedUserModelRepository.setPage(page);
            jpaPaginatedUserModelRepository.setSize(size);

            return new UserService<Page<User>>(jpaPaginatedUserModelRepository);
        } else {
            return new UserService<List<User>>(jpaListUserModelRepository);
        }
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public TicketService<?> ticketService(HttpServletRequest request) {
        String url = request.getRequestURI();
        if (url.contains("v2")) {
            int page = Integer.parseInt(Optional.ofNullable(request.getParameter("page")).orElse("0"));
            int size = Integer.parseInt(Optional.ofNullable(request.getParameter("size")).orElse("3"));

            jpaPaginatedTicketModelRepository.setPage(page);
            jpaPaginatedTicketModelRepository.setSize(size);

            return new TicketService<Page<User>>(jpaPaginatedTicketModelRepository);
        } else {
            return new TicketService<List<User>>(jpaListTicketModelRepository);
        }
    }
}
