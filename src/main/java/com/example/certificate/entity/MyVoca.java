package com.example.certificate.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table( indexes = {
        @Index(name = "idx_user_id", columnList = "userId")
})
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

    @Column
    private int examNum ; // 문제 번호



    public void setExamNum(int examNum ){
        this.examNum = examNum;
    }
    @Builder
    public MyVoca(String userId,String voca){
        this.userId = userId;
        this.voca = voca;
    }
}
