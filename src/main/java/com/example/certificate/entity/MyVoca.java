package com.example.certificate.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table
@Getter
@NoArgsConstructor
public class MyVoca { //내 단어장
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private  Long id;

    @Column
    private String userId;

    @Column
    private String  voca;

    @Builder
    public MyVoca(String userId,String voca){
        this.userId = userId;
        this.voca = voca;
    }
}
