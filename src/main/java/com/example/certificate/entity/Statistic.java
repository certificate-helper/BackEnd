package com.example.certificate.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class Statistic { //회원이 각 기출마다 푼 문제 대비 정답률을 알려줌
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String userId;


    @Column
    private  String timeStamp ; //시험 친 시간(년,월,일,시간,분,초)


    @ManyToOne
    @JoinColumn
    private WrongAnswer wrongAnswer;  //오답 문제 모음
    @Builder
    public  Statistic(String userId,String timeStamp,WrongAnswer wrongAnswer){
        this.userId = userId;
        this.timeStamp = timeStamp;
        this.wrongAnswer = wrongAnswer;
    }
}
