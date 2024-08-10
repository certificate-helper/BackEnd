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
    private  boolean isImage; //이미지가 존재하는 문제인지 (false이면 이미지가 없고 true이면 이미지 존재)
    @Column
    private byte[] imageData; //시험문제 이미지
    @Builder
    public ExamDto(String problem,String examNum,boolean isImage,byte[] imageData){
        this.problem = problem;
        this.examNum = examNum;
        this.isImage = isImage;
        this.imageData = imageData;
    }
}
