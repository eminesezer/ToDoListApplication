package com.eminesezer.todolistapplicationbackend.model.token;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class TokenRequest implements Serializable {
    private static final long serialVersionUID = 5989965961574744700L;
    private String username;
    private String password;

    public TokenRequest() {
        super();
    }
}
