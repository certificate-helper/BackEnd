package com.example.certificate.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class WrongAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private  Long id;


    @ManyToOne
    @JoinColumn
    private ExamLog examLog;

    @Column
    private String userId;



    @Column
    private String problem;  //문제

    @Column
    private String commentary; //오답에 대한 해설

    @Column
    private String category; //시험문제 단원 종류(sql,프로그래밍언어,보안 등등)

    @Column
    private int year; //기출 연도

    @Column
    private int round; //회차
    
    @Builder
    public  WrongAnswer(ExamLog examLog,String userId,String problem,String commentary,String category,int year,int round){
        this.examLog = examLog;
        this.userId = userId;
        this.problem = problem;
        this.commentary = commentary;
        this.category = category;
        this.year = year;
        this.round = round;
    }

}
