package com.example.certificate.dto;


import jakarta.persistence.Column;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VocaDto {
    @Column
    private String voca;

    @Column
    private String explain;

    @Column
    private String myVoca;

    @Builder
    public  VocaDto(String voca,String explain,String myVoca){
        this.voca = voca;
        this.explain = explain;
        this.myVoca = myVoca ;
    }
}
