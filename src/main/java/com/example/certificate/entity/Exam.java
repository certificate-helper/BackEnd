package com.example.certificate.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table
@Getter
@NoArgsConstructor
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @ManyToOne
    @JoinColumn
    private ExamLog examLog;

    @Column
    private  String problem;

    @Column
    private  String answer;
    @Column
    private int examNum; // 문제 번호

    @Column //단답형은 short,서술형은 long,순서문제는 order
    private String type; //문제 유형(단답형,서술형,순서문제 등등)

    @Column
    private String category; //시험문제 단원 종류(sql,프로그래밍언어,보안 등등)
    @Column
    private String imageUrl; //시험문제 이미지 저장주소

    private String state; // 문제의 상태를 기록 (안 푼 문제는 ?, 맞은 문제는 O, 틀린 문제는 X)

    public void updateAnswerCheck(String state) {
        this.state = state;
    } //

    @Builder
    public Exam(ExamLog examLog,String problem,String answer,int examNum,String type,String category,String imageUrl ) {
        this.examLog = examLog;
        this.problem = problem;
        this.answer = answer;
        this.examNum = examNum;
        this.state = "?";
        this.type = type;
        this.category = category;
        this.imageUrl = imageUrl; //이미지가 없으면 문자열 null
    }
}



