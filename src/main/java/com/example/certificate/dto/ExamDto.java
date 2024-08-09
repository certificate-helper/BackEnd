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

    @Column
    private  String type; //시험문제 유형(단답형은 short,서술형은 long,순서문제는 order)

    @Column
    private byte[] imageData; //시험문제 이미지
    @Builder
    public ExamDto(String problem,String examNum,String type,byte[] imageData){
        this.problem = problem;
        this.examNum = examNum;
        this.type = type;
        this.imageData = imageData;
    }
}
