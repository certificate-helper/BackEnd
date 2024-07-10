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
    private Long testId;

    
    @Builder
    public  WrongAnswer(String userId,Long testId){
        this.userId = userId;
        this.testId = testId;
    }

}
