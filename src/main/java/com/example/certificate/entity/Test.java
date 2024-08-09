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

    @Column //단답형은 short,서술형은 long,순서문제는 order
    private String type; //문제 유형(단답형,서술형,순서문제 등등)

    @Column
    private String category; //시험문제 단원 종류(sql,프로그래밍언어,보안 등등)
    @Column
    private String imageUrl; //시험문제 이미지 저장주소
    public void saveImageUrl(String imageUrl){this.imageUrl = imageUrl;}

    @Builder
    public Test(int year, int round, String problem, String answer, String type,String category) {
        this.year = year;
        this.round =round;
        this.problem = problem;
        this.answer = answer;
        this.type = type;
        this.category = category;
        this.imageUrl = "null";
    }
}
