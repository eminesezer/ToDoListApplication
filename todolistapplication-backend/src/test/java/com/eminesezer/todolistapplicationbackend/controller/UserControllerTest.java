package com.eminesezer.todolistapplicationbackend.controller;

import com.eminesezer.todolistapplicationbackend.config.CoreConfig;
import com.eminesezer.todolistapplicationbackend.config.WebSecurityConfig;
import com.eminesezer.todolistapplicationbackend.model.user.UserRequest;
import lombok.extern.log4j.Log4j2;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {CoreConfig.class, WebSecurityConfig.class})
@WebMvcTest(controllers = UserController.class)
@ActiveProfiles(value = "test")
public class UserControllerTest {

    private String URL = "http://localhost:12800";
    @Autowired
    private MockMvc mvc;

    @Test
    public void createUser() {

        try {
            MvcResult result = mvc.perform(MockMvcRequestBuilders.post(URL + "/registration")
                    .accept(MediaType.APPLICATION_JSON).content(String.valueOf(new UserRequest("es", "123", "123")))).andReturn();
            Assertions.assertEquals(200, result.getResponse().getStatus());
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }
}