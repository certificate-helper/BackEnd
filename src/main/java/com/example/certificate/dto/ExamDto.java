package com.example.certificate.dto;


import jakarta.persistence.Column;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExamDto {

    @Column
    private String problem;

    @Column
    private String examNum;

    @Builder
    public ExamDto(String problem,String examNum){
        this.problem = problem;
        this.examNum = examNum;
    }
}
