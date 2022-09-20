package com.example.carvolution.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.Mockito.when;

import com.example.carvolution.model.Adress;
import com.example.carvolution.model.User;
import com.example.carvolution.service.UserAdressService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = UserAdressController.class)
class UserAdressControllerTest { // TODO: should run against a inmem db

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private UserAdressService userAdressService;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void getAllUsers() throws Exception {
        List<User> users = new ArrayList<>();
        Adress adress1 = new Adress(1, "street1", "postcode1", "city1");
        User user1 = new User(1, "firstname1", "lastname1", adress1);
        User user2 = new User(2, "firstname2", "lastname2", adress1);
        users.add(user1);
        users.add(user2); // TODO: 2 newlines following


        when(userAdressService.getAllUsers()).thenReturn(users);

        mockMvc.perform(get("/carvolution/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.[0].id").value(1))
                .andExpect(jsonPath("$.[0].firstname").value("firstname1"))
                .andExpect(jsonPath("$.[0].lastname").value("lastname1"))
                .andExpect(jsonPath("$.[0].adress.id").value(1))
                .andExpect(jsonPath("$.[0].adress.street").value("street1"))
                .andExpect(jsonPath("$.[0].adress.postcode").value("postcode1"))
                .andExpect(jsonPath("$.[0].adress.city").value("city1"))
                .andExpect(jsonPath("$.[1].id").value(2))
                .andExpect(jsonPath("$.[1].firstname").value("firstname2"))
                .andExpect(jsonPath("$.[1].lastname").value("lastname2"))
                .andExpect(jsonPath("$.[1].adress.id").value(1))
                .andExpect(jsonPath("$.[1].adress.street").value("street1"))
                .andExpect(jsonPath("$.[1].adress.postcode").value("postcode1"))
                .andExpect(jsonPath("$.[1].adress.city").value("city1"));
    }

    @Test
    public void getUser() throws Exception {
        Adress adress1 = new Adress(1, "street1", "postcode1", "city1");
        User user1 = new User(1, "firstname1", "lastname1", adress1);

        when(userAdressService.getUser(eq(1))).thenReturn(user1);

        mockMvc.perform(get("/carvolution/user/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstname").value("firstname1"))
                .andExpect(jsonPath("$.lastname").value("lastname1"))
                .andExpect(jsonPath("$.adress.id").value(1))
                .andExpect(jsonPath("$.adress.street").value("street1"))
                .andExpect(jsonPath("$.adress.postcode").value("postcode1"))
                .andExpect(jsonPath("$.adress.city").value("city1"));
    }

    @Test
    public void saveUser() throws Exception {
        Adress adress1 = new Adress(1, "street1", "postcode1", "city1");
        User user1 = new User(1, "firstname1", "lastname1", adress1);

        when(userAdressService.saveUser(any())).thenReturn(user1);

        mockMvc.perform(post("/carvolution/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"lastname\": \"lastname1\"}"))
                .andExpect(status().isOk()); // TODO: useless newline

    }

    @Test
    public void updateAdress() throws Exception {
        Adress adress1 = new Adress("street1", "postcode1", "city1");
        User user1 = new User(1, "firstname1", "lastname1", adress1);

        when(userAdressService.updateAdress(adress1, 1)).thenReturn(adress1);

        mockMvc.perform(put("/carvolution/adress/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"lastname\": \"lastname1\"}"))
                .andExpect(status().isOk());
    }
}