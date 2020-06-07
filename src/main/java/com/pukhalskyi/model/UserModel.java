package com.pukhalskyi.model;

import com.pukhalskyi.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserModel {
    private Long id;
    private String nickname;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private Set<TicketModel> tickets;
}
