package com.example.certificate.dto;


import jakarta.persistence.Column;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AnswerRateDto {
    @Column
    private  String answerRate;

    @Builder
    public AnswerRateDto(String answerRate){
        this.answerRate = answerRate;
    }
}
