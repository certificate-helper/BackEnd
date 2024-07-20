package com.example.certificate.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//단어장
public class VocabularyList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private  Long id;


    @Column
    private String voca;

    @Column
    private String explain;

    @Builder
    public  VocabularyList(String voca,String explain){

        this.voca = voca;
        this.explain = explain;
    }

}
