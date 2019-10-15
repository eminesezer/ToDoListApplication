package com.eminesezer.todolistapplicationbackend.controller;

import com.eminesezer.todolistapplicationbackend.entity.User;
import com.eminesezer.todolistapplicationbackend.enumaration.AuthorityType;
import com.eminesezer.todolistapplicationbackend.model.user.UserRequest;
import com.eminesezer.todolistapplicationbackend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:30000")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private final UserRepository userRepository;

    @RequestMapping(value = "/registeration", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody UserRequest userInfo) {
        if (ObjectUtils.isEmpty(userInfo)) {
            return ResponseEntity.badRequest().body("User info is empty!");
        } else if (userInfo.getUsername().isEmpty() || userInfo.getPassword().isEmpty()) {
            return ResponseEntity.badRequest().body("User info detail is empty!");
        } else if (!userInfo.getPassword().equals(userInfo.getPasswordPlainText())) {
            return ResponseEntity.badRequest().body("User passwords are not matching!");
        }
        else if(!ObjectUtils.isEmpty(userRepository.findUserByUsername(userInfo.getUsername()))){
            return ResponseEntity.badRequest().body("Username is in use!");
        }
        String cryptedPassword = bCryptPasswordEncoder.encode(userInfo.getPassword());
        User newUser = User.builder().username(userInfo.getUsername())
                .password(cryptedPassword)
                .passwordPlaintext(userInfo.getPassword())
                .authorityType(AuthorityType.USER)
                .isEnabled(true).build();

        userRepository.save(newUser);
        return ResponseEntity.ok("User is saved successfully!");
    }

}
