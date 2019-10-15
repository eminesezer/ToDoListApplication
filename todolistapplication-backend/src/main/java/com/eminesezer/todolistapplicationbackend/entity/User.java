package com.eminesezer.todolistapplicationbackend.entity;

import com.eminesezer.todolistapplicationbackend.enumaration.AuthorityType;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "password_plaintext")
    private String passwordPlaintext;

    @Column(name = "authority_type")
    private AuthorityType authorityType;

    @Column(name = "is_enabled")
    private boolean isEnabled;
}
