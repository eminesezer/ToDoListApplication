package com.eminesezer.todolistapplicationbackend.entity;

import com.eminesezer.todolistapplicationbackend.enumaration.AuthorityType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username", length = 100)
    private String username;

    @Column(name = "password", length = 100)
    private String password;

    @Column(name = "password_plaintext", length = 255)
    private String passwordPlaintext;

    @Column(name = "authority_type")
    private AuthorityType authorityType;

    @Column(name = "is_enabled")
    private boolean isEnabled;

    public User(String username, String password, String passwordPlaintext, AuthorityType authorityType, boolean isEnabled) {
        this.username = username;
        this.password = password;
        this.passwordPlaintext = passwordPlaintext;
        this.authorityType = authorityType;
        this.isEnabled = isEnabled;
    }
}
