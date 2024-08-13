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


    
    @Builder
    public  WrongAnswer(ExamLog examLog,String userId,String problem,String commentary){
        this.examLog = examLog;
        this.userId = userId;
        this.problem = problem;
        this.commentary = commentary;

    }

}
