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
public class WrongQuiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private  Long id;

    @ManyToOne
    @JoinColumn
    private QuizLog quizLog;
    @Column
    private String userId;


    @Column
    private String problem;  //문제

    @Column
    private String commentary; //오답에 대한 해설

    @Builder
    public  WrongQuiz(QuizLog quizLog ,String userId,String problem,String commentary){
        this.quizLog = quizLog;
        this.userId = userId;
        this.problem = problem;
        this.commentary = commentary;

    }
}
