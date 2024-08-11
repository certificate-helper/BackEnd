package com.example.certificate;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ConvertDate { //현재 시간을 정수로 변환해주는 클래스
    LocalDateTime date;
    public  ConvertDate(LocalDateTime date){
        this.date = date;
    }
    public  Long  intDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String formattedNow = date.format(formatter);
        return Long.valueOf(formattedNow);
    }

}
