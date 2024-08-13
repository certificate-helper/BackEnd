package com.example.certificate.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class QuizFrequencyDto {
    @Column
    private String word;

    @Column
    private String count;

    @Builder
    public  QuizFrequencyDto(String word,String count){
        this.word = word;
        this.count = count;
    }

}
