package com.example.certificate;

public class StringConverter {

    public  String convertToUpperCase(String input) {
        // 소문자 영어를 대문자로 변환
        String result = "";
        for (char c : input.toCharArray()) {
            // 소문자 영어인지 확인 후 대문자로 변환
            if (c >= 'a' && c <= 'z') {
                result += Character.toUpperCase(c);
            } else {
                result += c;
            }
        }
        return result;
    }


}
