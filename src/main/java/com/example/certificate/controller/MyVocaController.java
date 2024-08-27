package com.example.certificate.controller;


import com.example.certificate.dto.ExamDto;
import com.example.certificate.dto.VocaDto;
import com.example.certificate.dto.WrongAnswerDto;
import com.example.certificate.entity.Exam;
import com.example.certificate.entity.MyVoca;
import com.example.certificate.service.ChatGptService;
import com.example.certificate.service.MyVocaService;
import com.example.certificate.service.VocaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class MyVocaController {
    private final MyVocaService myVocaService;
    private final VocaService vocaService;

    @PostMapping (value = "/saveMyVoca", consumes = "application/json", produces = "application/json")  //내 단어장에 저장하는 컨트롤러
    public void saveMyVoca(@RequestBody Map<String, String> requestData){
        System.out.println("save");
        //단어로 기본키 찾음
        myVocaService.saveMyVoca(requestData.get("id"),requestData.get("voca"));
    }
    @PostMapping(value = "/remove-my-voca", consumes = "application/json", produces = "application/json")
    public void removeMyVoca(@RequestBody Map<String, String> requestData){
        System.out.println("remove");
        myVocaService.removeMyVoca(requestData.get("id"),requestData.get("voca"));
    }
   // @PostMapping(value  = "/setQuiz") //단어 퀴즈 세팅 컨트롤러
    @PostMapping(value = "/setQuiz", consumes = "application/json", produces = "application/json")
    public void setQuiz(@RequestBody Map<String, String> requestData){
        String id = requestData.get("id");
        System.out.println("id: " + id);
        myVocaService.setQuiz(id);
    }

    @GetMapping (value = "/getQuizNum") //내가 치는 단어퀴즈 총 개수
    public int getQuizNum(@RequestParam("id") String id){
        return myVocaService.getAllExam(id).size();
    }

    @GetMapping (value = "/doQuiz") // 프론트쪽에서 번호를 누를 때마다 한문제씩 전달
    public ResponseEntity<ExamDto> doQuiz(@RequestParam("id") String id,
                                          @RequestParam("num") String num){
        System.out.println("num: "+num);
        ExamDto examDto = myVocaService.doQuiz(id,num);
        return new ResponseEntity<>(examDto, HttpStatus.OK);
    }

    @PostMapping (value="/checkAnswer")
    public void checkAnswer(@RequestParam("id") String id,
                            @RequestParam("voca") String voca,
                             @RequestParam("num") String num ){

                myVocaService.checkAnswer(id,voca,num);
    }
    @GetMapping  (value = "/wrongQuiz")
    public  List<WrongAnswerDto> wrongQuiz(@RequestParam("id") String id){
        return  myVocaService.wrongQuiz(id);
    }
}

