package com.example.certificate.controller;


import com.example.certificate.dto.AnswerRateDto;
import com.example.certificate.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StatisticController {
    //퀴즈 통계기능 :  정답률 , 가장 많이 틀린 단어
    private  final StatisticService statisticService;

    @GetMapping (value = "/get-quiz-answer-rate")
    public AnswerRateDto getQuizAnswerRate(@RequestParam("userId") String userId){
        return statisticService.getUserQuizLog(userId);
    }


}
