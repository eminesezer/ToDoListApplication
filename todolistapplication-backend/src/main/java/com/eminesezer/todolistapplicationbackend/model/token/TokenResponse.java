package com.eminesezer.todolistapplicationbackend.model.token;

import java.io.Serializable;

public class TokenResponse implements Serializable {
    private final String token;

    public TokenResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
