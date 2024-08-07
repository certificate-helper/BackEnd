package com.example.certificate.controller;

import com.example.certificate.dto.ExamDto;
import com.example.certificate.entity.Exam;
import com.example.certificate.entity.Test;
import com.example.certificate.entity.UserTest;
import com.example.certificate.service.TestService;
import lombok.RequiredArgsConstructor;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TestController {
    private final TestService testService;
    public static boolean isEnglish(String str) { //영어인지 판단하는 함수
        for (char ch : str.toCharArray()) {
            // 영어 문자의 유니코드 범위: A-Z (65-90), a-z (97-122)
            if ((ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z')) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    //    @PostMapping
//    public ResponseEntity<ProblemDto> checkAnswer(
//            @RequestParam("answer") String answer
//    ){
//
//    }
    public  UserTest getUserTest(String userId){
        LocalDate now = LocalDate.now();
        UserTest userTest = UserTest.
                builder().
                userId(userId). //유저 아이디
                        testDate(now).  //오늘 날짜
                        build();
        return userTest;
    }
    @PostMapping (value = "/setExam")  //  시험을 치기전 세팅을 해주는 controller
    public void setExam(@RequestParam("year") String year,
                        @RequestParam("round") String round,
                        @RequestParam("userId") String userId
                         ) {
        List<Test> tests = testService.getExam(year, round);  //시험 문제를 가져옴
        int num = 1; //문제 번호
        UserTest userTest = getUserTest(userId);
        for (Test test:tests){
            Exam exam = Exam.builder().
                    userTest(userTest).
                    //testId(test.getId()). //문제 아이디
                    examNum(num++).
                    state(0).
            build();
            testService.setExam(exam);
        }
    }


    @GetMapping ("/getExam")
    public ResponseEntity<ExamDto> getExam(@RequestParam("userId") String userId,
                                           @RequestParam("num") String num){
        ExamDto examDto = ExamDto.builder().build();
        return new ResponseEntity<>(examDto, HttpStatus.OK);
    }

}
