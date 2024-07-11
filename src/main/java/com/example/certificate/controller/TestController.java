package com.example.certificate.controller;

import com.example.certificate.dto.ProblemDto;
import com.example.certificate.entity.Exam;
import com.example.certificate.entity.Test;
import com.example.certificate.entity.UserTest;
import com.example.certificate.service.TestService;
import lombok.RequiredArgsConstructor;


import lombok.extern.slf4j.Slf4j;
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

//    @PostMapping
//    public ResponseEntity<ProblemDto> checkAnswer(
//            @RequestParam("answer") String answer
//    ){
//
//    }

    @PostMapping
    public void setExam(@RequestParam("year") String year,
                        @RequestParam("round") String round,
                        @RequestParam("userId") String userId
                         ) {
        LocalDate now = LocalDate.now();
        UserTest userTest = UserTest.
                builder().
                userId(userId).
                testDate(now).
                build();
        List<Test> tests = testService.getExam(year, round);  //시험 문제를 가져옴
        int num = 1; //문제 번호
        for (Test test:tests){
            Exam exam = Exam.builder().
                    userTest(userTest).
                    testId(test.getId()).
                    examNum(num++).
                    state(0).
            build();
            testService.setExam(exam);
        }
    }


}
