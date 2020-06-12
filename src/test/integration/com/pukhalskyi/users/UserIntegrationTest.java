package com.pukhalskyi.users;

import com.google.gson.Gson;
import com.pukhalskyi.TicketSystemApplication;
import com.pukhalskyi.dto.UserDto;
import com.pukhalskyi.entity.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = TicketSystemApplication.class)
@AutoConfigureMockMvc
@Sql({"/test-schema.sql", "/test-data.sql"})
public class UserIntegrationTest {

    @Autowired
    private MockMvc mvcMock;


    @Test
//    @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
    public void givenAllUsersRequest_whenFindAll_thenStatus200() throws Exception {
        mvcMock.perform(get("/v2/api/users/?page=0&size=6")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size").value("6"));
    }

    @Test
//    @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
    public void givenUsername_whenGetUserByUsername_thenStatus200() throws Exception {
        mvcMock.perform(get("/v1/api/users/test-bohdanpukhalskyi")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value("4"));
    }

    @Test
//    @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
    public void givenUserDto_whenSaveUser_thenStatus200() throws Exception {
        UserDto userDto = new UserDto(4L, "bohdanpukhalskyi", "1111", "Bohdan",
                "Pukhalskyi", "bohdanpukhalskyi@gmail.com", Role.ROLE_ADMIN, null);

        Gson gson = new Gson();

        String requestBody = gson.toJson(userDto);

        mvcMock.perform(post("/v1/api/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
//    @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
    public void givenUsername_whenDeleteUser_thenStatus200() throws Exception {
        mvcMock.perform(delete("/v1/api/users/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
