package com.pukhalskyi.tickets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.pukhalskyi.TicketSystemApplication;
import com.pukhalskyi.dto.TicketDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

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
public class TicketIntegrationTest {
    public static final String TICKETS_V1_URL = "/v1/api/tickets";

    @Autowired
    private MockMvc mvcMock;


    @Test
    @WithMockUser(username = "user", password = "user", roles = {"USER"})
    public void givenAllTicketsRequest_whenFindAll_thenStatus200() throws Exception {
        mvcMock.perform(get("/v2/api/tickets/?page=0&size=4")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.size").value("4"));
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
    public void givenTicketDto_whenSaveTicket_thenStatus200() throws Exception {
        LocalDateTime boughtAt = LocalDateTime.of(LocalDate.of(2020, 05, 14), LocalTime.of(02, 46, 00));
        TicketDto ticketDto = new TicketDto(6L, boughtAt, 6, 6, 60.0, null, null);

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();

        String requestBody = gson.toJson(ticketDto);

        mvcMock.perform(post(TICKETS_V1_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "admin", password = "admin", roles = {"ADMIN"})
    public void givenUsername_whenDeleteTicket_thenStatus200() throws Exception {
        mvcMock.perform(delete(TICKETS_V1_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = {"USER"})
    public void givenEventTitle_whenFindByEvent_thenStatus200() throws Exception {
        mvcMock.perform(get(TICKETS_V1_URL + "/event/test-motoball: Podillia - Zoria")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user", password = "user", roles = {"USER"})
    public void givenFromAndToDateTimes_whenFindByDateRange_thenStatus200() throws Exception {
        mvcMock.perform(get(TICKETS_V1_URL + "/date/from/2020-05-15T00:00:00/to/2020-05-18T23:59:59")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenPlaceTitle_whenFindByPlace_thenStatus200() throws Exception {
        mvcMock.perform(get(TICKETS_V1_URL + "/place/test-Motoball Stadium")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"));
    }

    class LocalDateTimeAdapter implements JsonSerializer<LocalDateTime> {

        public JsonElement serialize(LocalDateTime dateTime, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        }
    }


}
