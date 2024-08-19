package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="user")

public class User extends BaseEntity{

    @Column(name = "username",nullable = false,columnDefinition = "varchar(100)")
    private String username;

    @Column(name = "name",nullable = false,columnDefinition = "varchar(100)")
    private String name;

    @Column(name = "email",nullable = false,columnDefinition = "varchar(100)")
    private String email;

    @Column(name = "password",nullable = false,columnDefinition = "varchar(100)")
    private String password;
}
