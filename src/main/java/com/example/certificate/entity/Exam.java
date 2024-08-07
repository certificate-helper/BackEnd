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
    @JoinColumn(name = "user_test_id")
    private UserTest userTest;


    @ManyToOne
    @JoinColumn
    private VocabularyList vocabularyList;


    @Column
    private int examNum; // 문제 번호

    @Column
    private int state; // 문제의 상태를 기록 (안 푼 문제는 0, 맞은 문제는 1, 틀린 문제는 -1)

    public void updateAnswerCheck(int state) {
        this.state = state;
    }

    @Builder
    public Exam(UserTest userTest,VocabularyList vocabularyList,int examNum, int state) {
        this.userTest = userTest;
        this.vocabularyList = vocabularyList;
        this.examNum = examNum;
        this.state = state;
    }
}



