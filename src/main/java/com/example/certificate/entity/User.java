package com.example.certificate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @Column
    private String userId;
    @Column
    private String password;
    @Column
    private List<String> test;


    @Builder
    public User(String userId,String password,List<String> test){
        this.userId = userId;
        this.password = password;
        this.test = test;
    }
}
