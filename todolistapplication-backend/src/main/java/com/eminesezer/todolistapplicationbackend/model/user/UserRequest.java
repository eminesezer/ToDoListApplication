package com.eminesezer.todolistapplicationbackend.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRequest {
    private String username;
    private String password;
    private String passwordPlainText;

    public UserRequest() {
        super();
    }
}