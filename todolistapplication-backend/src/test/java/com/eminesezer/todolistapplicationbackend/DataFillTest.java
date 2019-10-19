package com.eminesezer.todolistapplicationbackend;

import com.eminesezer.todolistapplicationbackend.config.CoreConfig;
import com.eminesezer.todolistapplicationbackend.entity.User;
import com.eminesezer.todolistapplicationbackend.enumaration.AuthorityType;
import com.eminesezer.todolistapplicationbackend.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {CoreConfig.class})
@ActiveProfiles(value = "dev")
public class DataFillTest {

    @Autowired
    UserRepository userRepository;

    @Test
    public void contextLoads() {

        User newUser = User.builder().username("deneme")
                .password("$2a$10$bLV4avJSBdTaizX/Y7TUZO/8DOCA.HRzEJbm5ya3TLO.NwcFiVHZ6")
                .passwordPlaintext("Pa9F5Fa5bEvxVhd7")
                .isEnabled(true)
                .authorityType(AuthorityType.ADMIN)
                .build();
        userRepository.save(newUser);
    }
}
