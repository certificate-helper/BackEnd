package com.example.certificate.entity;


import jakarta.persistence.*;
import lombok.*;
//모든 기출의 데이터베이스
@Entity
@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private  Long id;

    @Column
    private int year; //기출 연도

    @Column
    private int round; //회차

    @Column
    private String problem; //문제

    @Column
    private String answer ; //정답

    @Column
    private String type; //문제 유형
    @Builder
    public Test(int year, int round, String problem, String answer, String type) {
        this.year = year;
        this.round =round;
        this.problem = problem;
        this.answer = answer;
        this.type = type;
    }
}
