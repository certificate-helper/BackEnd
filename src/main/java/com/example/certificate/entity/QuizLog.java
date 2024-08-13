package com.example.certificate.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table
@Getter
@NoArgsConstructor
public class QuizLog { //단어 시험을 칠 때
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private Long quizTime; //퀴즈를 친 시간

    @Column
    private String userId; //아이디

    @Column
    private int totalNum; //총 문제 수

    @Column
    private int wrongAnswerNum;

    public void updateWrongAnswerNum(int wrongAnswerNum){
        this.wrongAnswerNum =wrongAnswerNum;
    }

    @Builder
    public QuizLog(Long quizTime,String userId,int totalNum){
        this.quizTime = quizTime;
        this.userId = userId;
        this.totalNum = totalNum;
        this.wrongAnswerNum = 0 ;

    }
}
