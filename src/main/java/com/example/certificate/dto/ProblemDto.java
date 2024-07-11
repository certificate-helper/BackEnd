package com.example.certificate.dto;


import jakarta.persistence.Column;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProblemDto {

    @Column
    private String problem;

    @Builder
    public ProblemDto(String problem){
        this.problem = problem;
    }
}
