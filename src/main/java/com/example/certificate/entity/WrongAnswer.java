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


    @Column
    private String userId;


    @Column
    private String problem;  //문제

    @Column
    private String commentary; //오답에 대한 해설

    
    @Builder
    public  WrongAnswer(String userId,String problem,String commentary){
        this.userId = userId;
        this.problem = problem;
        this.commentary = commentary;
    }

}
