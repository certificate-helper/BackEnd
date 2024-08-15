package com.example.certificate.dto;


import jakarta.persistence.Column;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryCoutDto {
    @Column
    private String category;


    @Column
    private String count;

    @Builder
    public CategoryCoutDto(String category,String count){
        this.category = category;
        this.count = count;
    }
}
