package com.example.certificate.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table
@Getter
@NoArgsConstructor
public class ExamLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String userId;

    @Column
    private Long examDate ;




    @Builder
    public ExamLog(String userId,Long examDate){
        this.userId = userId;
        this.examDate = examDate;
    }
}
