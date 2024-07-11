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
    private String userId;
    @Column
    private String myVoca;

    @Column
    private String myExplain;

    @Builder
    public  VocabularyList(String userId,String myVoca,String myExplain){
        this.userId = userId;
        this.myVoca = myVoca;
        this.myExplain = myExplain;
    }

}
