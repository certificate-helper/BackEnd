package com.example.certificate.controller;

import com.example.certificate.dto.ExamDto;
import com.example.certificate.dto.WrongAnswerDto;
import com.example.certificate.entity.Exam;
import com.example.certificate.entity.Test;
import com.example.certificate.entity.UserTest;
import com.example.certificate.service.TestService;
import lombok.RequiredArgsConstructor;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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
    @PostMapping (value = "/insertTest")
    public  void insertTest(@RequestParam("year") int year,
                            @RequestParam("round") int round,
                            @RequestParam("type") String type,
                            @RequestParam("problem") String problem,
                            @RequestParam("answer") String answer,
                            @RequestParam("category") String category,
                            @RequestParam("image") MultipartFile image
                            ){
            testService.insertTest(year,round,type,problem,answer,category,image);
    }
    @PostMapping (value = "/set-exam", consumes = "application/json", produces = "application/json")  //  시험을 치기전 세팅을 해주는 컨트롤러
    public void setExam(@RequestBody Map<String, String> requestData
                         ) {
         testService.setExam(requestData.get("id"),requestData.get("year"),requestData.get("round"));  //시험 문제를 가져옴
    }

    @GetMapping ("/do-exam") //시험을 문제를 제공하는 컨트롤러
    public ResponseEntity<ExamDto> getExam(@RequestParam("id") String id,
                                           @RequestParam("num") String num){
        System.out.println("do-exam. id: "+id+" num: "+num);
        ExamDto examDto = testService.getExam(id,num);
        return new ResponseEntity<>(examDto, HttpStatus.OK);
    }
    @PostMapping (value="/check-exam-answer",consumes = "application/json", produces = "application/json") //기출문제 정답을 확인하는 컨트롤러
    public void checkExamAnswer(@RequestBody Map<String, String> requestData){
        System.out.println("check-answer. id: "+requestData.get("id")+" answer: "+requestData.get("answer")+" num: "+requestData.get("num"));
        testService.checkExamAnswer(requestData.get("id"),requestData.get("answer"),requestData.get("num"));
    }


//    @GetMapping(value = "wrong-exam")
//    public ResponseEntity<WrongAnswerDto> wrongExam(@RequestParam("id") String id){
//
//    }
}
