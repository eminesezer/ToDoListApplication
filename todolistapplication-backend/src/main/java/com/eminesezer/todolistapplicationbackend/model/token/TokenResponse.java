package com.eminesezer.todolistapplicationbackend.model.token;

import java.io.Serializable;

public class TokenResponse implements Serializable {
    private static final long serialVersionUID = 6909392620864260607L;
    private final String token;

    public TokenResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
