package com.example.certificate.dto;


import jakarta.persistence.Column;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class WrongAnswerDto { //오답에 대한 해설을 제공하는 DTO

    @Column
    private String problem; //문제
    @Column
    private String answer; //정답
    @Column
    private String userInput; //사용자가 입력한 답안
    @Column
    private String commentary; //오답에 대한 해설

    @Builder
    public WrongAnswerDto(String problem,String answer,String userInput,String commentary){
        this.problem = problem;
        this.answer = answer;
        this.userInput = userInput;
        this.commentary = commentary;
    }
}
