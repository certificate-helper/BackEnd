package com.example.certificate.entity;



import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table
@Getter
@NoArgsConstructor
public class UserTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;


    @Column
    private String userId;


    @Column
    private LocalDate testDate; // 시험친 날짜

    @Builder
    public UserTest(String userId, LocalDate testDate) {

        this.userId = userId;
        this.testDate = testDate;
    }
}
