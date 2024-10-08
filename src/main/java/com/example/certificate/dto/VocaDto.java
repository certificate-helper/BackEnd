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
    private int num;
    @Column
    private String voca;

    @Column
    private String explain;

    @Column
    private boolean selected;

    @Builder
    public  VocaDto(int num,String voca,String explain,boolean selected){
        this.num = num;
        this.voca = voca;
        this.explain = explain;
        this.selected = selected ;
    }
}
