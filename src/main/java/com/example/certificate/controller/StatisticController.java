package com.example.certificate.controller;


import com.example.certificate.dto.AnswerRateDto;
import com.example.certificate.dto.QuizFrequencyDto;
import com.example.certificate.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StatisticController {
    //퀴즈 통계기능 :  정답률 , 가장 많이 틀린 단어
    private  final StatisticService statisticService;

    @GetMapping (value = "/get-quiz-answer-rate")
    public AnswerRateDto getQuizAnswerRate(@RequestParam("id") String id){
        return statisticService.getUserQuizLog(id);
    }


    @GetMapping (value = "/get-quiz-frequency")
    public List<QuizFrequencyDto> getProblemFrequency(@RequestParam("id") String id){
        return statisticService.getProblemFrequency(id);
    }

    @GetMapping (value = "/get-quiz-answer-num")
    public List<AnswerRateDto> getQuizAnswerNum(@RequestParam("id") String id){
        return statisticService.getQuizAnswerNum(id);
    }

}
